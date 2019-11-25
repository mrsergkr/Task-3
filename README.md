Написаны автоматические тесты, покрывающие следующие кейсы:

Поиск транспорта по направлению (класс searchForTransportInDirectionTests).
1. Пользователь открывает сайт https://rasp.yandex.ru 
2. Вводит пункт отправления “Кемерово”
3. Вводит пункт назначения “Москва”
4. Вводит дату 7 сентября 
5. Нажимает Найти
6. Проверяет, что отображается название рейса.
7. Проверяет, что у направления есть время в пути.
8. Проверяет, что у всех рейсов есть иконка транспорта.
9. Проверяет, что рейсов отобразилось 5.


Поиск транспорта по нехорошему направлению (класс searchForTransportInBadDirectionTests).
1. Пользователь открывает сайт https://rasp.yandex.ru
2. Вводит пункт отправления “Кемерово проспект Ленина”
3. Вводит пункт назначения “Кемерово Бакинский переулок”
4. Вводит дату на ближайшую среду
5. Нажимает на «Автобус»
5. Нажимает Найти
6. Проверяет, что отображается ошибка с текстом «Пункт прибытия не найден. Проверьте правильность написания или выберите другой город.»

Классы Page, MainPage и SearchResultsPage являются вспомогательными.