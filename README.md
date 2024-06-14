# Eco Haul Connect

## Descrição:

O Eco Haul Connect é um app que facilita a conexão entre pessoas que desejam descartar ou doar móveis, livros e outros items e prestadores de serviço que possuem o veículo apropriado para esse tipo de serviço.

## O app:

O Cliente pode abrir um novo serviço, determinando o preço que quer pagar e a data.
Ele pode incluir informações dos itens que deseja transportar, inclusive com imagens.

## Como rodar:

- Clone o repositório e rode-o no Android Studio.
- A classe `br.com.connect.ecohaulconnect.network.services.ApiClient.kt` contém o parâmetro de url para se conectar com a api
- No momento a api está hospedada com a opção de que o servidor para de funcionar por desuso, então é melhor rodar a api localmente
- Siga as instruções do README da "EcoHaulConnect_API_ClientesServicos" para rodar a API localmente na porta 8080.
- Em ApiClient coloque o endereço de IP local da máquina + 8080 para que o emulador do AndroidStudio seja capaz de encontrá-la
- Com a Api rodando, na tela de login clique, em Criar Conta, e acesse o app. 