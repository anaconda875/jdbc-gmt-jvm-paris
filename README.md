Table:
```sql
create table theentity (
        theid integer not null,
        thevalue timestamp(6),
        primary key (theid)
)
```
Result:

```
theid |      thevalue       
-------+---------------------
     1 | 1900-01-01 00:00:02
     2 | 1900-01-01 00:00:01
     3 | 1900-01-01 00:00:00
     4 | 1899-12-31 23:09:20
     5 | 1899-12-31 23:09:19
(5 rows)
```
