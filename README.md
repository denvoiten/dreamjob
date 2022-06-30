# Проект - Dream job

## О проекте

* Приложение представляет собой биржу труда с web-интерфейсом
* Пользователь может быть как кандидатом так и HR. Кандидаты могут вносить в систему данные о себе:
![](images/addCandidate.png)
* HR могут публиковать вакансии о работе:
![](images/addVac.png)
* Только авторизованные пользователи могут просматривать списки вакансий и кандидатов,
  а также добавлять новые. Авторизация построена на основе фильтра (класс AuthFilter). 
* Все зарегистрированные пользователи
  хранятся в БД. Форма авторизации:
![](images/login.png)
* Есть возможность регистрации новых пользователей. Форма регистрации: 
![](images/reg.png)
* В качестве системы логирования используется связка log4j и slf4j.
* Для отображения авторизованного пользователя на всех страницах используется объект Session.


## Использование

Переходим на главную [страницу](http://localhost:8080/index) приложения:
Регистрируемся:

![Регистрация](images/reg.png)


Авторизуемся:


![Авторизация](images/login.png)

Добавим вакансию:
![Добавление вакансии](images/addVac.png)

Отредактируем добавленную вакансию:
![Редактирование вакансии](images/editVacancy.png)

Добавим кандидата:
![Добавление кандидата](images/addCandidate.png)

Проверим, что информация на главной странице обновилась:
![Главная](images/main.png)

### Контакты:
[<img align="left" alt="telegram" width="18px" src="https://cdn.jsdelivr.net/npm/simple-icons@3.3.0/icons/telegram.svg" />][telegram]
[<img align="left" alt="gmail" width="18px" src="https://cdn.jsdelivr.net/npm/simple-icons@3.3.0/icons/gmail.svg" />][gmail]
[<img align="left" alt="LinkedIn" width="18px" src="https://cdn.jsdelivr.net/npm/simple-icons@v3/icons/linkedin.svg" />][linkedin]


[telegram]: https://t.me/GrokDen
[gmail]: mailto:den.voiten@gmail.com
[linkedin]: https://www.linkedin.com/in/denis-voytenko-585488117/
