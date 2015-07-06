# [Confitura 2015](http://tech.viacom.com/warsawsdc/confitura2015/)

## What is wrong with this solution?

Even though it could (after some tweaks) process data from stream it still requires a lot of memory
to hold every distinct cached item in the `dataCache` map.

If this data doesn't fit into memory of a single machine I imagine that we would need to use some kind of fast similarity 
function to make "buckets" of similar data rows. Then the computation could be split and distributed on multiple 
machines to try to remove duplicates in that smaller groups (probably using this naive implementation).

After every data chunk is done we could join any two results by comparing the `HashCache` and `DataCache`. This means 
that we could join chunks together until we have only one with no duplicates.

## Why I haven't implemented it?

This is really very tempting but my day has only 24 hours.

## Complexity of the naive implementation

Well... It *looks* like `O(n)` but the effectiveness of the algorithm really depends on the quality of the `hashCode()` 
method and the cost to lookup `DataCache`. The `DataCache` lookup occurs only when the hash function generates colliding
hashes or we have a duplicated item. The `BitSet` lookup is negligible (`O(1)`).

In this implementation the `DataCache` is built from `mutable.HashMap` and `mutable.HashSet` which (according to 
[documentation](http://docs.scala-lang.org/overviews/collections/performance-characteristics.html)) have effectively 
constant lookup and add time.

## Duplicates

You have *very big* list of elements. Please provide best solution to detect and remove duplicated elements.

Please provide a solution and **comments** about its benefits and drawbacks. Please give us complexity (`O(n)`, `O(n^2)`, `O(ln(n))`, ...). Please think about custom classes like:

```java
class Person {
    String name;
    int age;
}
```

You can check contest bye-laws [here](http://tech.viacom.com/warsawsdc/confitura2015/Regulamin_konkurs_Viacom_programmer_adventure_2015.pdf).

Check out our Confitura 2015 site [here](http://tech.viacom.com/warsawsdc/confitura2015/)

We are hiring! Visit our [career site](http://tech.viacom.com/careers/).
