import scala.collection.mutable.{Set, HashMap, MultiMap, BitSet}

class NaiveDuplicateRemover[T](val data: List[T]) {
  type HashCache = BitSet
  type DataCache = HashMap[Int, Set[T]] with MultiMap[Int, T]

  val hashCache = new BitSet
  val dataCache = new HashMap[Int, Set[T]] with MultiMap[Int, T]

  def removeDuplicates(): List[T] = data filter withCache

  private def withCache: T => Boolean =
    t => {
      val hash = t.hashCode()
      if (inCache(hash, t)) false
      else {
        extendCache(hash, t)
        true
      }
    }

  private def inCache(hash: Int, item: T) = hashCache.contains(hash) && dataCache(hash).contains(item)

  private def extendCache(hash: Int, item: T) = {
    hashCache += hash
    dataCache.addBinding(hash, item)
  }
}
