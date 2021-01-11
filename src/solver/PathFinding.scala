package solver

import maze.{GridLocation, MapTile, PhysicsVector}


object PathFinding {
  def findPath(start: GridLocation, end: GridLocation, map: List[List[MapTile]]): List[GridLocation] = {
    val graph:Graph[GridLocation] = new Graph()

    var startGraphIndex = -1
    var endGraphIndex = -1

    val rowLength = map(0).length
    val columnLength = map.length
    var graphIndex = 0

    for(y <- 0 until columnLength){
      for(x <- 0 until rowLength){
        //Crete Nodes for all
        graph.addNode(graphIndex,new GridLocation(x,y))

        //Check Up
        if(y > 0 && map.apply(y-1).apply(x).passable == true && map.apply(y).apply(x).tileType != "wall"){
          graph.addEdge(graphIndex,graphIndex-rowLength)
        }

        //Check Down
        if(y < columnLength-1 && map.apply(y+1).apply(x).passable == true && map.apply(y).apply(x).tileType != "wall"){
          graph.addNode(graphIndex+rowLength,new GridLocation(x,y+1))
          graph.addEdge(graphIndex,graphIndex+rowLength)
        }

        //Check Left
        if(x > 0 && map.apply(y).apply(x-1).passable == true && map.apply(y).apply(x).tileType != "wall"){
          graph.addEdge(graphIndex,graphIndex-1)
        }

        //Check Right
        if(x < rowLength-1 && map.apply(y).apply(x+1).passable == true && map.apply(y).apply(x).tileType != "wall"){
          graph.addNode(graphIndex+1,new GridLocation(x+1,y))
          graph.addEdge(graphIndex,graphIndex+1)
        }

        //Start Cord
        if(x == start.x && y == start.y){
          startGraphIndex = graphIndex
        }

        //End Cord
        if(x == end.x && y == end.y){
          endGraphIndex = graphIndex
        }
        graphIndex = graphIndex+1
      }
    }

    //BFS
    var explored: Set[Int] = Set(startGraphIndex)

    var distance: Map[Int, Int] = Map()
    distance += startGraphIndex -> -1

    val toExplore: Queue[Int] = new Queue()
    toExplore.enqueue(startGraphIndex)

    while (!toExplore.empty()) {
      val nodeToExplore = toExplore.dequeue()
      for (node <- graph.adjacencyList(nodeToExplore)) {
        if(!explored.contains(node)){
          distance += node -> nodeToExplore
          toExplore.enqueue(node)
          explored = explored + node
        }
      }
    }

    //PathTracker
    var path: List[GridLocation] = List()
    if(map.apply(graph.nodes(endGraphIndex).y).apply(graph.nodes(endGraphIndex).x).tileType == "ground" || map.apply(graph.nodes(endGraphIndex).y).apply(graph.nodes(endGraphIndex).x).tileType == "goal"){
      var nodeTracker = distance(endGraphIndex)
      if(nodeTracker == -1){
        path = List()
      }
      else{
        path = graph.nodes(endGraphIndex):: path
        path = graph.nodes(distance(endGraphIndex))::path
        while(nodeTracker != startGraphIndex){
          path = graph.nodes(distance(nodeTracker))::path
          nodeTracker = distance(nodeTracker)
        }
      }
    }

    println(graph.nodes(endGraphIndex))
    path
  }

  def getVelocity(path: List[GridLocation], currentLocation: PhysicsVector): PhysicsVector = {
    if(path.length > 0){
      val floorX = currentLocation.x.toInt
      val floorY = currentLocation.y.toInt
      var pathIndex = path.length
      for(i <- 0 until path.length){
        if(path(i).x == floorX && path(i).y == floorY){
          pathIndex = i
        }
      }

      if(path.last == path(pathIndex)){
        val velocity: PhysicsVector = new PhysicsVector((path.last.x+0.5)-currentLocation.x,(path.last.y+0.5)-currentLocation.y).normal2d()
        val vector: PhysicsVector = new PhysicsVector(velocity.x * 5, velocity.y * 5)
        if(currentLocation.distance2d(new PhysicsVector(path.last.x + 0.5, path.last.y + 0.5)) <= 0.1 ){
          new PhysicsVector(0,0)
        }
        else{
          vector
        }
      }

      else{
        val velocity: PhysicsVector = new PhysicsVector((path(pathIndex+1).x+0.5)-currentLocation.x , (path(pathIndex+1).y+0.5)-currentLocation.y).normal2d()
        val vector: PhysicsVector = new PhysicsVector(velocity.x *5,velocity.y*5)
        vector
      }
//
//      if(path(pathIndex) == path.last){
//        var TempnextTileX = path(pathIndex).x + 0.5
//        var TempnextTileY = path(pathIndex).y + 0.5
//        var nextTileX = TempnextTileX-currentLocation.x
//        var nextTileY = TempnextTileY-currentLocation.y
//        var vector = new PhysicsVector(nextTileX,nextTileY).normal2d()
//        toReturn = new PhysicsVector(vector.x * 5, nextTileY * 5)
//      }
//
//      else if(path(pathIndex) == path.last && currentLocation.distance2d(toReturn) <= 0.1){
//        toReturn = new PhysicsVector(0.0,0.0)
//      }
//
//
//      else{
//        var TempnextTileX = path(pathIndex+1).x + 0.5
//        var TempnextTileY = path(pathIndex+1).y + 0.5
//        var nextTileX = TempnextTileX-currentLocation.x
//        var nextTileY = TempnextTileY-currentLocation.y
//
//        var vector = new PhysicsVector(nextTileX,nextTileY)
//        vector = vector.normal2d()
//        toReturn = new PhysicsVector(vector.x * 5, vector.y *5)
//      }
//

    }
    else{
      new PhysicsVector(0,0)
    }
  }

}


