### OMNIBOT

- Um BOT automático para o Discord que pode ser configurado diretamente
pela ``settings.json``, tem a capacidade de criar um bot relacionado ao
desenho Ben 10, contém Omnitrix, Empregos e muito mais!

> Antes do BOT ser testado, é necessário que esta documentação
> seja inteiramente lida, todas as informações são relevantes!

### 🛠️ HOSPEDAGEM
- Esta aplicação foi criada com o objtivo de ser o seu próprio inicializador do BOT, você pode inicializar ele pela tela
padrão que aparece ao inicializar a aplciação, pode ver algumas configurações básicas, e pode configurar ele pela
configuração citada um poucoacima, então não é necessário pagar hospedagem obrigatóriamente, pode apenas abrir em seu
dispositivo e deixá-lo aberto.

![image](https://imgur.com/aEhen8n.png)

### 📝 SALVAMENTO DE DADOS
- O principal diferencial desta aplicação é que os dados dos seus usuários são armazenados em um tipo específico de Banco de Dados,
sendo ele o **(SQLITE)**, ele será hospedado na pasta da aplicação, ele será acessado sempre que um dado for modificado, é pedido para
que não modifique ele sem ter o conhecimento do que está fazendo.

![image](https://imgur.com/RtjRu0m.png)

### ⚙️ CONFIGURAÇÃO
- Além de muitos pontos positivos que esta aplicação já tem, a configuração é razoávelmente simples, além de ter sido feita em um
ambiente bem simples de ser usado, o JSON **(JavaScript Object Notation)**, abaixo será mostrada uma parte da configuração desta
aplicação.

```java
{
  "BOT_CONFIGURATION": {
    "NAME": "OmniBOT",
    "TOKEN": "SEU TOKEN",
    "VISIBILITY": "ONLINE",
    "STATE": "PLAYING",
    "MESSAGE": "o Omnitrix!"
  },
  "OMINIBOT_CONFIGURATION": {
    "PREFIX": "!",
    "OWNER_ID": "SEU ID",
    "LOGS_CHANNEL": "O CANAL DE LOGS"
  },
```

### 💻 COMANDOS
> /criar - Cria um perfil para um usuário Discord.
> /perfil - Envia informações sobre o perfil de um usuário.

### DEMONSTRAÇÃO
- Uma demonstração do comando ``/perfil [usuário]``, algo bem simples,
os dados abaixo foram puxados do Banco de Dados local onde está sendo hospedada
a aplicação, esta é uma representação não customizável atualmente.

[!image](https://imgur.com/8sx5CHt.png)
