# API de Serviços Financeiros

Sistema com as funcionalidades de realizar depósito, saque, transferência e visualizar saldo em conta.

Para executar esta aplicação basta rodar o comando `docker-compose up -d` no terminal na mesma pasta que está o documento docker-compose.yml

Observação: hoje tenho a v1 implementada, prestes a implementar a v2. Especificações sobre cada versão no final deste README.

## Endpoints:

1. Resetar banco `POST /reset`

2. Visualizar saldo em conta `GET /balance?account_id=1234`

3. Depositar em conta `POST /event`

Payload:
```javascript
{
"type":"deposit", 
"destination":"100", 
"amount":10
}
```

4. Sacar de conta `POST /event`

Payload:
```javascript
{
"type":"withdraw", 
"destination":"100", 
"amount":10
}
```

5. Transferir entre contas `POST /event`

   Payload:
```javascript
{
"type":"withdraw", 
"origin":"100", 
"destination":"50", 
"amount":10
}
```

![ideias de projeto-Page-3 drawio](https://user-images.githubusercontent.com/26654992/226224990-b12d4b30-4393-4564-9791-9ab31c85c3a4.png)

![](https://media.giphy.com/media/67ThRZlYBvibtdF9JH/giphy.gif)
