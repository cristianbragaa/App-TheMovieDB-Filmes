# App-TheMovieDB-Filmes
 Aplicativo criado com intuito de praticar conhecimentos obtidos no curso
 
 # Foi utilizado
 SplashScreen, Retrofit, Dagger Hilt, Picasso, Arquitetura MVVM + Clean
 
 # Sobre o aplicativo
 Com o objetivo de praticar a arquitetura recomendada MVVM junto com Clean, foi utilizado UseCase nesse app.
 O app faz requisição para a api TheMovieDB que recupera filmes, são duas telas apenas.
 
 A primeira com a requisição que lista os filmes populares.
 
 A segunda requisição trás os detalhes do filme clicado, e a terceira trás os filmes similares ao filme clicado caso tenha um resultado da api.
 
 Nesse aplicativo optei por utilizar retorno de interfaces na camada de dados e também utilizar um model próprio para cada camada da aplicação. A aplicação é separada em 3 camadas (presentation, domain, data).
 
 Com isso pude separar o app em camadas de responsabilidades únicas e próprias, fazendo com que cada camada não dependa uma da outra diretamente e também não conheça seus dados, tornando o app mais escalável, com baixo acoplamento entre as camadas e de fácil manutenção, e mais testável também. 
 
 
 

