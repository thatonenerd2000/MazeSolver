package maze

import play.api.libs.json.{JsValue, Json}


object GameMap {

  def apply(): GameMap = {
    new GameMap {
      tiles = List(
        MapTile.generateRow("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"),
        MapTile.generateRow("O----------------------------O"),
        MapTile.generateRow("O-O-OOOO-OOO-O-O-O-OOOOOOOOO-O"),
        MapTile.generateRow("O-O----------O---O-O---------O"),
        MapTile.generateRow("O-OOOOOOOOOOOOOO-O-OOOOOOOOOOO"),
        MapTile.generateRow("O-O-O----------OOO--O---OO---O"),
        MapTile.generateRow("O-O---OO-OOOOOOO-OO-O-O-OO-O-O"),
        MapTile.generateRow("O-OOOOO--O-----O--O---O-OO-O-O"),
        MapTile.generateRow("O-------OO-OOO-OO-OOOOO----O-O"),
        MapTile.generateRow("OOO-OOOOO--O-O-OO-OO-OOOOOOO-O"),
        MapTile.generateRow("O--------------------------OGO"),
        MapTile.generateRow("OO-OOOOOOOOOOOOOOOOOOOOOOOOO-O"),
        MapTile.generateRow("O--OO---O---O---O---O---O--O-O"),
        MapTile.generateRow("O-OO--O-O-O-O-O-O-O-O-O-O----O"),
        MapTile.generateRow("O----OO-O-O---O---O---O---OOOO"),
        MapTile.generateRow("OOOOOO--O-OOOOOOOOOOOOOOOOOOOO"),
        MapTile.generateRow("O------OO--------------------O"),
        MapTile.generateRow("O-OOOOOOOOOOOOOOOOOOOOOOOOOO-O"),
        MapTile.generateRow("O----------------------------O"),
        MapTile.generateRow("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOO")
      )
      startingLocation = new GridLocation(1, 10)
    }
//    new GameMap {
//      tiles = List(
//        MapTile.generateRow("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"),
//        MapTile.generateRow("O----------------------------O"),
//        MapTile.generateRow("O----------------------------O"),
//        MapTile.generateRow("O----------------------------O"),
//        MapTile.generateRow("O----------------------------O"),
//        MapTile.generateRow("O-------------OOOO-----------O"),
//        MapTile.generateRow("O---------OOOOOOOOOOO--------O"),
//        MapTile.generateRow("O--------OOO------OOOOOO-----O"),
//        MapTile.generateRow("O-------OO----------OOO------O"),
//        MapTile.generateRow("O-------OOO----------OG------O"),
//        MapTile.generateRow("O--------OO---------OOO------O"),
//        MapTile.generateRow("O---------OOO-------OOO------O"),
//        MapTile.generateRow("O--------------OOOOOOOOOOOO--O"),
//        MapTile.generateRow("O---------OOOOOOOO-----------O"),
//        MapTile.generateRow("O---------------OOOOO--------O"),
//        MapTile.generateRow("O---------------OOOOO--------O"),
//        MapTile.generateRow("O----------------------------O"),
//        MapTile.generateRow("O----------------------------O"),
//        MapTile.generateRow("O----------------------------O"),
//        MapTile.generateRow("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOO")
//      )
//      startingLocation = new GridLocation(15, 4)
//    }
  }

}

class GameMap {
  var tiles: List[List[MapTile]] = List()
  var startingLocation = new GridLocation(0, 0)

  def tilesJSON(): JsValue = {
    Json.toJson(tiles.map((row: List[MapTile]) => row.map((tile: MapTile) => Json.toJson(
      Map("type" -> Json.toJson(tile.tileType), "passable" -> Json.toJson(tile.passable))
    ))))
  }

}
