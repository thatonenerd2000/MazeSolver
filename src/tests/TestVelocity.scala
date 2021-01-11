package tests

import maze.{GridLocation, PhysicsVector}
import org.scalatest.FunSuite
import solver._

class TestVelocity extends FunSuite {

  val EPSILON: Double = 0.01

  def equalDoubles(d1: Double, d2: Double): Boolean = {
    (d1 - d2).abs < EPSILON
  }

  def equalsVectors(v1: PhysicsVector, v2: PhysicsVector): Boolean = {
    equalDoubles(v1.x, v2.x) && equalDoubles(v1.y, v2.y) && equalDoubles(v1.z, v2.z)
  }


  test("test pathToDirection 1") {
    val pathList: List[GridLocation] = List(new GridLocation(1,2), new GridLocation(2,2), new GridLocation(3,2))
    val startPoint: PhysicsVector = new PhysicsVector(1.5,2.5)
    val compareVec: PhysicsVector = new PhysicsVector(5.0,0.0,0.0)

    assert(equalsVectors(PathFinding.getVelocity(pathList,startPoint),compareVec))
  }

  test("test pathToDirection 2") {
    val pathList: List[GridLocation] = List(new GridLocation(11,12), new GridLocation(10,12), new GridLocation(9,12), new GridLocation(9,13), new GridLocation(9,14), new GridLocation(10,14))
    val startPoint: PhysicsVector = new PhysicsVector(9.55,14.1)
    val compareVec: PhysicsVector = new PhysicsVector(4.6,1.95,0.0)

    assert(equalsVectors(PathFinding.getVelocity(pathList,startPoint),compareVec))
  }

  test("test pathToDirection 3") {
    val pathList: List[GridLocation] = List(new GridLocation(11,12), new GridLocation(10,12), new GridLocation(9,12), new GridLocation(9,13), new GridLocation(9,14), new GridLocation(10,14))
    val startPoint: PhysicsVector = new PhysicsVector(10.48,14.49)
    val compareVec: PhysicsVector = new PhysicsVector(0.0,0.0,0.0)

    assert(equalsVectors(PathFinding.getVelocity(pathList,startPoint),compareVec))
  }

  test("test pathToDirection 4") {
    val pathList: List[GridLocation] = List(new GridLocation(11,12), new GridLocation(10,12), new GridLocation(9,12), new GridLocation(9,13), new GridLocation(9,14), new GridLocation(10,14))
    val startPoint: PhysicsVector = new PhysicsVector(10.9,14.52)
    val compareVec: PhysicsVector = new PhysicsVector(0.0,0.0,0.0)

    assert(equalsVectors(PathFinding.getVelocity(pathList,startPoint),compareVec))
  }

}
