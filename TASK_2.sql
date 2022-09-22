create table Country (
    CountryID int not null,
    Name varchar not null,
    constraint PK_Country primary key(CountryID),
    constraint UK_Country_Name unique (Name)
);


insert into Country(CountryID, Name) 
values (1, 'Azerbaijan'),
       (2, 'Turkey'),
       (3, 'Georgia');
       

create table City(
    CityID int not null,
    CountryID int not null,
    Name varchar not null,
    Population int not null,
    constraint PK_City primary key(CityID),
    constraint FK_City_CountryID foreign key (CountryID) references Country(CountryID),
    constraint UK_City_CountryID_Name unique (CountryID, Name)
);


insert into City(CityID, CountryID, Name, Population) 
values (1, 1, 'Baku', 2277500),
       (2, 1, 'Sumgait', 343100),
       (3, 1, 'Ganja', 332600),
       (4, 1, 'Lankaran', 226900),
       (5, 2, 'Istanbul', 14025646),
       (6, 2, 'Ankara', 4587558),
       (7, 2, 'Izmir', 2847691),
       (8, 3, 'Seville',  63),
       (9, 3, 'Mershon', 48),
       (10, 3, 'Manor', 10);


create table Building(
    BuildingID int not null,
    CityID int not null,
    Name varchar not null,
    Floors int not null,
    constraint PK_Building primary key(BuildingID),
    constraint FK_Building_CityID foreign key (CityID) references City(CityID)
);


insert into Building(BuildingID, CityID, Name, Floors)
values (1, 1, 'Massiv D', 9),
       (2, 1, 'Delfin', 12),
       (3, 2, 'Apartman 12', 17);


--Task 1: 
--Select countries where the total number of inhabitants (population) in all cities is greater than 400.

--Solution 1
select 
    cty.CountryID as country_id,
    cty.Name as country_name,
    sum(c.Population) as total_population
from Country cty 
join City c on cty.CountryID = c.CountryID
group by cty.CountryID
having sum(c.population) > 400;


--Solution 2
select 
    t.country_id,
    t.country_name,
    t.total_population
from (select 
          row_number() over (partition by cty.CountryID) as rn,
          cty.CountryID as country_id,
          cty.Name as country_name,
          sum(c.Population) over (partition by cty.CountryID) as total_population
      from Country cty
      join City c on cty.CountryID = c.CountryID) t
where t.rn = 1 and t.total_population > 400;


--Task 2
--Select names of the countries that have no buildings at all.
select t.country_name
from (select 
          row_number() over (partition by cty.CountryID) as rn,
          cty.name as country_name,
          count(b.BuildingID) over (partition by cty.CountryID) as building_count
      from Country cty
      full join City c on cty.CountryID = c.CountryID
      full join Building b on c.CityID = b.CityID
) t
where t.rn = 1 and t.building_count = 0;


