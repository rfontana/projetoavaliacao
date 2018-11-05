# projetoavaliacao
Projeto de Avaliação para processo seletivo

O candidato deve criar um serviço com Spring Boot escolhendo o assunto, é necessário que tenham duas tabelas e um relacionamento direto entre elas.

Objetivos:
O serviço deve ter um ‘endpoint’ responsável por gravar as informações obtidas em um banco de dados relacional (da preferência do candidato), após a chamada do ‘endpoint’ o dado obtido deve ser enviado a um ‘producer’ de fila JMS (preferência ao ActiveMQ).

Criar um ‘consumer’ para processar a mensagem produzida no passo anterior e efetuando a persistência da mesma no banco de dados com JPA.

O serviço deve ter também um ‘endpoint’ responsável pela consulta dos dados salvos e apresentar em uma consulta paginada ao usuário final.

- O serviço deve ser construído em Java.
- O serviço deve ser um projeto Maven.
- O serviço deve ser undertow.
- Teste Unitário, ‘Devtools’, ‘actuator’ e ‘swagger’ são necessários.
- Obrigatório usar Spring Data.
- Organização das pastas será analisada.
- O controlador de versão deve ser GIT e desenvolvido sobre duas ‘branchs’.
- O repositório do GIT deve ser publico


Após finalizar o projeto deve enviar a ‘url’ do repositório para análise.
___________________________________________________________________________________

Teste sugerido:

http://localhost:8080/swagger-ui.html#/invoice-controller/newInvoiceUsingPOST

POST ---> http://localhost:8080/invoices/

Msg Body
---
{
  "amountPaid": 750,
  "currencyCode": "BRL",
  "date": "2018-11-05T20:07:25.536Z",
  "tax": 0,
  "total": 750,
  "transactions": [
    {
      "amount": 750,
      "currencyCode": "BRL",
      "date": "2018-11-05T20:07:25.536Z",
      "maskedCardNumber": "************0011",
      "paymentMethod": "CARD"
    }
  ]
}
---

