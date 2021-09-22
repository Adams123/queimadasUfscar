# ReadME
## Queimadas UFSCar

![](app/src/main/res/drawable/logo.png)

Repositório contendo código fonte do app frontend do Brigada Online, com licensa MIT.
Tecnologias usadas:
| Plugin | Versão |
| ------ | ------ |
| Kotlin | 1.5.10 |
| Gradle | 7.0.0 |
| API Base | 22 |
| JVM Source | 15 |

Para mudar o endereço de chamada de API, mudar a propriedade `BASE_URL` no arquivo `src/main/utils/Constants.kt` para o endereço desejado.
Também é necessário atualizar o valor da `API_KEY` em `src/main/AndroidManifest.xml` e no `gradle.properties` para uma chave de acesso de desenvolvedor. Para mais informações de como conseguir uma chave de desenvolvedor, acesse: [maps api]

[//]: #
[maps api]: <https://developers.google.com/maps/documentation/javascript/get-api-key>