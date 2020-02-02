# Прототип социальной сети

https://a902285-sn.herokuapp.com/

#### Задачи
<details>
  <summary>TASK 01</summary>
  
  Требуется разработать создание и просмотр анект в социальной сети.
  
  Функциональные требования:
  Авторизация по паролю.
  Страница регистрации, где указывается следующая информация:
  Имя
  Фамилия
  Возраст
  Пол
  Интересы
  Город
  Страницы с анкетой.
  
  Нефункциональные требования:
  Любой язык программирования
  В качестве базы данных использовать MySQL
  Не использовать ORM
  Программа должна представлять из себя монолитное приложение.
  Не рекомендуется использовать следующие технологии:
  Репликация
  Шардинг
  Индексы
  Кэширование
  
  
  Верстка не важна. Подойдет самая примитивная.
  
  Разместить приложение на любом хостинге. Например, heroku.
  
</details>

#### Стек
- kotlin
- react js
- mysql

#### Локальный запуск:

##### Требования к ПО:
- jdk 1.8
- nodejs 12.14.1
- docker / docker-compose

##### Запуск приложения
```
cd ./docker/
docker-compose up -d

cd ../
./gradlew clean build
java -jar ./backend/build/libs/backend-0.0.1-SNAPSHOT.jar
```
Перейти по ссылке: http://localhost:8080/