## Business-logic simulation
Реализуется бизнесс-логика управления фотоальбомами\
[Исходная реализация](https://www.stranamam.ru)

### Политики разграничения доступа
Роли:
+ `USER`
+ `MODERATOR`

Организация доступа к фотоальбомам различных пользователей организована
для роли `USER` на логическом уровне в виде [классификации](./demo/src/main/java/business/application/demo/repo/entity/UserRestriction.java).