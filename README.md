# hello-clojure

Testing out Clojure and Leiningen by making a generic table generator.

The function `(hello-clojure.table/create)` takes a title, a list of columns and a vector of records to display.

The width and alignments of the table and each column is automatically determined to accommodate the size of the data provided.

## Compile and Execute

You need Java, Clojure and Leiningen.

```bash
$ lein run
```

## Sample Output

```
-----------------------------------------------
            SEINFELD CAST (BY AGE)
-----------------------------------------------
Character         Actor                 Born
-----------------------------------------------
Elaine Benes      Julia Louis Dreyfus   1961
George Costanza   Jason Alexander       1959
Newman            Wayne Knight          1955
Jerry Seinfeld    Jerry Seinfeld        1954
J. Peterman       John O'Hurley         1954
Cosmo Kramer      Michael Richards      1949
Larry David       Larry David           1947
-----------------------------------------------
```
