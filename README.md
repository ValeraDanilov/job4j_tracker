# Tracker

<h2>О проекте</h2>

<p>Предназначен для закрепления знаний, полученных при изучении блока посвященного ООП.</p>
<p>Этот проект - консольное приложение. После запуска пользователю отображается меню с возможностями программы. Программа просит ввести в консоль пункт меню для дальнейшего действия. Например, пользователь ввел цифру 0. Система просит пользователя ввести имя заявки. После этого действия система сохраняет в памяти заявку и снова отображает пункты меню. Если пользователь выбрал пункт 6 - это будет выход из программы, т.е. программа закрывается.</p>
<p style="text-align: center">

  <img src="img/img.png" height="200" title="главное меню программы">
  <img src="img/img_1.png" height="200" title="добавление элемента">
  <img src="img/img_2.png" height="200" title="редактирование элемента">
  <img src="img/img_3.png" height="200" title="удаление элемента">
  <img src="img/img_4.png" height="200" title="вывод всех элементов">
  <img src="img/img_5.png" height="200" title="вывод элемента по имени">
  <img src="img/img_6.png" height="200" title="вывод элемента по айди">
  <img src="img/img_7.png" height="200" title="выход">
</p>
<h2>Структура проекта</h2>
<ul>
  <li>Данные будут храниться в памяти. Хранилище будет описывать класс <a href="https://github.com/ValeraDanilov/job4j_tracker/blob/main/src/main/java/ru/job4j/tracker/store/MemTracker.java">ru.job4j.tracker.store.MemTracker</a></li>
  <li>Так же описано хранилище основанное на базе данных <a href="https://github.com/ValeraDanilov/job4j_tracker/blob/main/src/main/java/ru/job4j/tracker/jdbc/SqlTracker.java">ru.job4j.tracker.jdbc.SqlTracker</a></li>
  <li>Hibernate <a href="https://github.com/ValeraDanilov/job4j_tracker/blob/main/src/main/java/ru/job4j/tracker/hbn/HbmTracker.java">ru.job4j.tracker.hbn.HbmTracker</a> </li>
  <li>Объект этого класса будет описывать модель данных <a href="https://github.com/ValeraDanilov/job4j_tracker/blob/main/src/main/java/ru/job4j/tracker/Item.java">ru.job4j.tracker.Item</a></li>
  <li>Объект для управления меню <a href="https://github.com/ValeraDanilov/job4j_tracker/blob/main/src/main/java/ru/job4j/tracker/StartUI.java">ru.job4j.tracker.StartUI</a></li>
</ul>