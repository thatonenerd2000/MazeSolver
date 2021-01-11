package solver

class Graph[A] {

  var nodes: Map[Int, A] = Map()
  var adjacencyList: Map[Int, List[Int]] = Map()


  def addNode(index: Int, a: A): Unit = {
    nodes += index -> a
    adjacencyList += index -> List()
  }

  def addEdge(index1: Int, index2: Int): Unit = {
    adjacencyList += index1 -> (index2 :: adjacencyList(index1))
    adjacencyList += index2 -> (index1 :: adjacencyList(index2))
  }

  def connected(index1: Int, index2: Int): Boolean = {
    adjacencyList(index1).contains(index2)
  }

  def isPath(path: List[Int]): Boolean = {
    // initialize prev to an invalid node id
    var prev = nodes.keys.min - 1
    var valid = true
    for (index <- path) {
      if (prev != nodes.keys.min - 1) {
        if (!connected(prev, index)) {
          valid = false
        }
      }
      prev = index
    }
    valid
  }

  def areConnected(index1: Int, index2: Int): Boolean = {
    // TODO: Return true if the two nodes are connected by a path, false otherwise
    var explored: Set[Int] = Set(index1)

    var distance: Map[Int, Int] = Map()
    distance += index1 -> -1

    val toExplore: Queue[Int] = new Queue()
    toExplore.enqueue(index1)

    while (!toExplore.empty()) {
      val nodeToExplore = toExplore.dequeue()
      for (node <- adjacencyList(nodeToExplore)) {
        if(!explored.contains(node)){
          distance += node -> nodeToExplore
          toExplore.enqueue(node)
          explored = explored + node
        }
      }
    }
    if(explored.contains(index2)){
      true
    }
    else{
      false
    }
  }

  def distance(index1: Int, index2: Int): Int = {
    // TODO: Return the distance between index1 and index2 in this graph
    // You may assume that the two nodes are connected
    var explored: Set[Int] = Set(index1)

    var distance: Map[Int, Int] = Map()
    distance += index1 -> -1

    val toExplore: Queue[Int] = new Queue()
    toExplore.enqueue(index1)

    while (!toExplore.empty()) {
      val nodeToExplore = toExplore.dequeue()
      for (node <- adjacencyList(nodeToExplore)) {
        if(!explored.contains(node)){
          distance += node -> nodeToExplore
          toExplore.enqueue(node)
          explored = explored + node
        }
      }
    }

    println(distance)
    var distanceTracker: Int = 1
    var nodeTracker = distance(index2)
    if(nodeTracker == -1){
      distanceTracker = 0
    }
    else{
      while(nodeTracker != index1){
        distanceTracker+=1
        nodeTracker = distance(nodeTracker)
      }
    }
    distanceTracker
  }
}

object Graph {
  def main(args: Array[String]): Unit = {
    val graph: Graph[String] = new Graph()
    graph.addNode(0, "UCLA")
    graph.addNode(1, "STANFORD")
    graph.addNode(2, "SRI")
    graph.addNode(3, "UCSB")
    graph.addNode(4, "RAND")
    graph.addNode(5, "UTAH")
    graph.addNode(6, "SDC")
    graph.addNode(7, "MIT")
    graph.addNode(8, "BBN")
    graph.addNode(9, "LINCOLN")
    graph.addNode(10, "CARNEGIE")
    graph.addNode(11, "HARVARD")
    graph.addNode(12, "CASE")

    graph.addEdge(0,1)
    graph.addEdge(0,2)
    graph.addEdge(0,3)
    graph.addEdge(0,4)
    graph.addEdge(1,2)
    graph.addEdge(2,3)
    graph.addEdge(3,4)
    graph.addEdge(2,5)
    graph.addEdge(4,6)
    graph.addEdge(5,6)
    graph.addEdge(5,7)
    graph.addEdge(4,8)
    graph.addEdge(7,8)
    graph.addEdge(7,9)
    graph.addEdge(9,12)
    graph.addEdge(12,10)
    graph.addEdge(10,11)
    graph.addEdge(11,8)

    println(graph.distance(0,10))
  }
}
