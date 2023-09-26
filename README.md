# Компилирование и запуск 
Сборка проекта осуществляется с помощью Gradle
  1. Заходим в папку проекта.
  2. Пишем в консоль ```gradle build```
  3. Запускаем через ```gradle bootRun```
     (Либо можно через редактор Intellij idea)
# Конфигурация
Изначально бд лежит на http://localhost:8080.<br>
При желании в [файле конфигурации]([https://github.com/user/repo/blob/branch/other_file.md](https://github.com/F0G0/CFT_test/blob/master/src/main/resources/application.properties)https://github.com/F0G0/CFT_test/blob/master/src/main/resources/application.properties) можно поменять порт добавив: ```server.port= [желаемый порт]```
Так же я оставил включенной консоль базы данных, она находится по ссылке http://localhost:8080/h2-ui. При желании можно её отключить изменив ```spring.h2.console.enabled=true``` на ```false```.
# Прочее
Я сделал две версии приложения, одна сначала отправляет все отрезки в бд, а потом объединяет их [v2](https://github.com/F0G0/CFT_test/tree/v2). Это было сделано для избежания случаев потери записей во время их склеивания.<br>
Вторая версия склеивает все отрезки перед их отправкой, а так же перед отправкой get запроса. Она должна быть менее затратная по запросам [v1](https://github.com/F0G0/CFT_test/tree/v1).
