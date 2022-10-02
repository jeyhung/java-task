insert into menus(id, name, creation_at)
values (1, 'Polish', CURRENT_TIMESTAMP),
       (2, 'Mexican', CURRENT_TIMESTAMP),
       (3, 'Italian', CURRENT_TIMESTAMP);

insert into menu_items (id, menu_id, name, price)
values (1, 1, 'Pierogi', 3.2),
       (2, 1, 'Rosół', 1.92),
       (3, 1, 'Łazanki', 3.2),
       (4, 1, 'CocaCola', 1),
       (5, 2, 'Sausage with potatoes', 0.75),
       (6, 2, 'Orange juice 1.5L', 1.2);