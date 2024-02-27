Desafio técnico GFT:

Descrição do desafio (Opção 2):
Um ecosistema baseado em micro serviços, que se comunicam entre si via eventos(Publisher/Subscriber)
Exemplo de caso de uso: Agência de viagem, a cada reserva feita pelo usuário, um quarto, carro e assento de avião são resrvados.

Solução:
- Dois micro serviços: BookingAPI e BookingService, implementados em Java utilizando Spring Boot, que se integram por um barramento de eventos Kafka nas criações de novas reservas: vôo, hotel e carro, de forma assincrona, permitindo um grande volume de entrada de dados e processamento das requisiçõs de reservas para uma agência de viagens.
- O eco sistema também disponibiliza um API Rest para visualizar as reservas já feitas, modificá-las e excluí-las, de forma sincrona (Aqui, poderiamos implementar o modelo arquitetural CQRS, separando as ações de query e persistencia em dois novos micro serviços tornado a aplicação mais performatica e robusta).
- O micro serviço BookingAPI é um BFF, recebendo as requisições de novos agendamentos e imediatamente repassando (Publisher) esta para o barramento de eventos (Kafka), num tópico chamado booking-topic, onde a aplicação BookingService está subescrito(Subscriber). Ao receber o push no listener, este micro serviço processa as informações, efetiva as reservas e persiste seus dados em um banco de dados Postgres (Docker)
- A aplicação BookingService, implementa um padrão de pacotes que permite a integração com Web Services externos, uma API Rest ou SOAP por exemplo, fornecidos pelos sistemas de hoteis, companias aéreas e locadores de automóveis, com baixo acoplamento e facilidade para outros tipos de integrações. 
- O BookingService, ao criar uma reserva, por ser uma operação assincrona, ao tentar efetivar as reservas nos sistemas integrados (vôo, estadia e locação de carro), envia notificações para o cliente, usuário da aplicação, informando os numeros de checking para sua utilização ou a notificação de falha no caso do respectivo serviço de reserva não esteja disponível. 
- Logs de aplicação são gerados para análise dos fluxos de reservas.

- Apache Kafka:
Para o desenvovimento local utilizando o Kafka, foi utilizado uma ferramenta chamada Conduktor, segue o link de refêrencia: https://docs.conduktor.io/platform/, que além que subir o serviço do barramentos de eventos (Publish/Subscribe), fornece uma interface grafica para visualização dos brokers, esquemas, tópicos, consumidores, dentre outros recursos para configuração do Apache Kafka. O endereço e porta padrão para comunicação é o: http://localhost:9092 

- Docker Postgres:
Para subir a aplicação local, é necessário ter o Docker instalado e subir um conteiner com banco de dados Postgres. Seguem algumas referências:
https://www.docker.com
https://docs.docker.com/desktop/
https://www.docker.com/products/docker-hub/

Uma vez instalado o docker local, basta executar o comando abaixo para subir um container docker com Postgres acessível na porta 5432:

docker run -p 5432:5432 -e POSTGRES_PASSWORD=1234 postgres

Note que a senha definida para acessar a instância do Postgres é: 1234

- Acesso ao banco:
Para acesso ao banco de dados, sugiro o DBeaver que por ser baseado em java, pode ser executado em qualquer plataforma. Segue referência:
https://dbeaver.io

- Base de dados para persistência das reservas pelo BookingService:
É necessário criar a base de dados via comando SQL, para isso basta executar o comando abaixo no DBeaver por exemplo, ou qualquer outro client de sua preferência:

create database booking_storage

Obs.: As tabelas são criadas automaticamente devido a configuração do hibernate na aplicação BookingService, no applicationProperties.xml . Esta configuração também dropa e cria novas tabelas a cada subida do micro serviço BookingService.

- Requisições HTTP via Postman
Está disponibilizado uma collection de requisições HTTP do Postman para ser importado e permitir o acesso aos recursos da API BookingAPI, nome do arquivo: GFT_BookingAPI_Postman_Collection.json. Abaixo uma referência do Postman:
https://www.postman.com

- Instruções para subir todo o eco sistema do Booking:

1 - executar o Kafka conductor;
2 - executar o Docker e subir o container do Postgres
3 - executar o DBeaver ou outro client Postgres de sua preferência e criar a base de dados: booking_storage, via comando SQL
4 - subir o micro serviço BookingAPI via IDE de sua preferência (ou por linha de comnado)
5 - subir o micro serviço BookingService via IDE de sua preferência (ou por linha de comnado)
6 - executar o Postman, importar a collection de requisições HTTP disponibilizada neste diretório (GFT_BookingAPI_Postman_Collection.json)