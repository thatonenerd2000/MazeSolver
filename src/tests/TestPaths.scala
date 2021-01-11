package tests

import maze.GridLocation
import org.scalatest._
import solver._

class TestPaths extends FunSuite {


  test("test path 1") {
    val level: Int = 0
    TestingMaps(level).tiles
    assert(PathFinding.findPath(new GridLocation(3,12),new GridLocation(3,8),TestingMaps(level).tiles) == List(new GridLocation(3,12),new GridLocation(3,11), new GridLocation(3,10) ,new GridLocation(3,9), new GridLocation(3,8)))

  }

  test("test path 2") {
    val level: Int = 0
    TestingMaps(level).tiles
    assert(PathFinding.findPath(new GridLocation(5,5),new GridLocation(8,8),TestingMaps(level).tiles) == List(new GridLocation(5,5),new GridLocation(5,6), new GridLocation(5,7) ,new GridLocation(5,8), new GridLocation(6,8), new GridLocation(7,8), new GridLocation(8,8)))
  }

  test("test path 2 on tile 2"){
    val level: Int = 1
    TestingMaps(level).tiles

    assert(PathFinding.findPath(new GridLocation(15,10),new GridLocation(15,4),TestingMaps(level).tiles) == List(new GridLocation(15,9),new GridLocation(15,8), new GridLocation(15,7) ,new GridLocation(15,6), new GridLocation(15,5), new GridLocation(15,4)))

  }


}