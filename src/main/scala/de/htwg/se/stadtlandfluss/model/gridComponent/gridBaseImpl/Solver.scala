package de.htwg.se.stadtlandfluss.model.gridComponent.gridBaseImpl

import de.htwg.se.stadtlandfluss.model.Round
import de.htwg.se.stadtlandfluss.model.gridComponent.GridInterface

case class Solver() {

  val land: Vector[String] = Vector("Afghanistan", "Ägypten", "Albanien", "Algerien", "Andorra", "Angola", "Antigua und Barbuda", "Äquatorialguinea", "Argentinien", "Armenien", "Aserbaidschan", "Äthiopien",
    "Australien", "Bahamas", "Bahrain", "Bangladesch", "Barbados", "Belgien", "Belize", "Benin", "Bhutan", "Bolivien", "Bosnien und Herzegowina", "Botsuana", "Brasilien", "Brunei Darussalam", "Bulgarien", "Burkina Faso", "Burundi", "Chile", "China", "Cookinseln", "Costa Rica", "Côte d'Ivoire", "Dänemark", "Deutschland", "Dominica", "Dominikanische Republik", "Dschibuti", "Ecuador", "El Salvador", "Eritrea", "Estland", "Fidschi", "Finnland", "Frankreich", "Gabun", "Gambia", "Georgien", "Ghana",
    "Grenada", "Griechenland", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Indien", "Indonesien", "Irak",
    "Iran", "Irland", "Island", "Israel", "Italien", "Jamaika", "Japan", "Jemen", "Jordanien", "Kambodscha", "Kamerun", "Kanada", "Kap Verde", "Kasachstan", "Katar", "Kenia", "Kirgisistan",
    "Kiribati", "Kolumbien", "Komoren", "Kongo", "Kongo Demokratische Republik", "Korea Demokratische Volksrepublik", "Korea Republik", "Kosovo", "Kroatien", "Kuba", "Kuwait", "Laos", "Lesotho", "Lettland",
    "Libanon", "Liberia", "Libyen", "Liechtenstein", "Litauen", "Luxemburg", "Madagaskar", "Malawi", "Malaysia", "Malediven", "Mali", "Malta",
    "Marokko", "Marshallinseln", "Mauretanien", "Mauritius", "Mazedonien", "Mexiko", "Mikronesien", "Moldau", "Monaco", "Mongolei", "Montenegro", "Mosambik", "Myanmar", "Namibia", "Nauru",
    "Nepal", "Neuseeland", "Nicaragua", "Niederlande", "Niger", "Nigeria", "Niue", "Norwegen", "Oman", "Österreich", "Pakistan", "Palau", "Panama", "Papua-Neuguinea",
    "Paraguay", "Peru", "Philippinen", "Polen", "Portugal", "Ruanda", "Rumänien", "Russische Föderation", "alomonen", "Sambia", "Samoa", "San Marino",
    "Tomé und Príncipe", "Saudi-Arabien", "Schweden", "Schweiz", "Senegal", "Serbien", "Seychellen", "Sierra Leone", "Simbabwe", "Singapur", "Slowakei", "Slowenien", "Somalia", "Spanien",
    "Sri Lanka", "St. Kitts und Nevis", "St. Lucia", "St. Vincent und die Grenadinen", "Südafrika", "Sudan", "Südsudan", "Suriname", "Swasiland", "Syrien", "Tadschikistan", "Tansania", "Thailand", "Timor-Leste", "Togo", "Tonga", "Trinidad und Tobago", "Tschad", "Tschechische Republik", "Tunesien", "Türkei", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "Ungarn", "Uruguay", "Usbekistan", "Vanuatu", "Vatikanstadt", "Venezuela", "Vereinigte Arabische Emirate",
    "Vereinigte Staaten", "Vereinigtes Königreich", "Vietnam", "Weißrussland", "Zentralafrikanische Republik", "Zypern");

  val fluss: Vector[String] = Vector("Ager", "Aist", "Alm", "Bregenzerach", "Donau", "Dornbirner Ach", "Drau", "Enns", "Erlauf", "Feistritz", "Fischa", "Gail", "Gasteiner Ache", "Glan", "Gürtschitz", "Grossache", "Grosse Mhül", "Gurk", "Ill", "Inn", "Isel", "Kainach", "Kamp", "Krems", "Lafnitz", "Lavant", "Lech", "Leinsitz", "Leitha", "Lieser", "Liesingbach", "Malta", "March", "Mur", "Müll", "März", "Ötztaler Ache", "Palten", "Pielach", "Piesting", "Pinka", "Pitze", "Raab", "Rhein", "Saalach", "Salza", "Salzach", "Schwarza", "Schwechat", "Seeache", "Sill", "Sulm", "Steyr", "Taurach", "Thaya", "Traisen", "Traun", "Triesting", "Wien", "Ysper", "Zederhausbach", "Ziller");
  val automarke: Vector[String] = Vector("Acura ", "Aixam ", "Alfa Romeo ", "Ariel ", "Aston Martin ", "Audi ", "Bentley ", "Bitter ", "Brilliance ", "Bristol ", "Bugatti ", "Buick ", "BMW ", "Cadilla ", "Callaway ", "Caterhem ", "Chevrolet ", "CityEL ", "Chrysler ", "Citroen ", "Commuter Cars ", "Dacia ", "Daewoo ", "Daihatsu ", "De Tomaso ", "Dodge ", "Donkervoort ", "Ferarri ", "Fiat ", "Fisker ", "Ford ", "GAS ", "Ginetta ", "GMC ", "Gumpert ", "Heuliez ", "Holden ", "Honda ", "Hummer ", "Hyundai ", "Infiniti ", "Iran Khodro ", "Isdera ", "Isuzu ", "Jaguar ", "Jeep ", "Karmann ", "Kia ", "Koenigsegg ", "Lada ", "Lamborghini ", "Lancia ", "Land-Rover ", "Lexus ", "Ligier ", "Lincoln ", "Lola ", "Lotus ", "LTI ", "Mahindra ", "Marcos ", "Maruti ", "Maserati ", "Matra ", "Maybach ", "Mazda ", "McLaren ", "Mercedes-Benz ", "Merlin ", "MG ", "Microcar ", "Mini ", "Mitsubishi ", "Mitsuoka ", "Morgan ", "Moskwitsch ", "Nanjing ", "Nissan ", "Oldsmobile ", "Opel ", "Pagani ", "Panoz ", "Peugeot ", "Pininfarina ", "Pontiac ", "Porsche ", "Proton ", "Radical ", "Reliant ", "Renault ", "Rinspeed ", "Rolls-Royce ", "Rover ", "Saab ", "Saleen ", "SAS ", "Scion ", "Seat ", "Skoda ", "Smart ", "Spyker ", "SsangYong ", "Subaru ", "Suzuki ", "Tata ", "Tatra ", "Toyota ", "TVR ", "Twike ", "Vauxhall ", "Volvo ", "VW-Volkswagen ", "Wiesmann ", "Zagato ", "ZAP ", "Zastava ", "Zender");
  //Stadt zu gross
  val farbe: Vector[String] = Vector("ufeffaltrosa", "aquamarinblau", "beige", "biskuit", "blassgrün", "blasstürkis", "blau", "blauviolett", "braun", "bunt", "chiffongelb", "creme", "cyan", "distel", "dunkelblau", "dunkelgrün", "dunkeltürkis", "elfenbein", "frühlingsgrün", "gelb", "gelbbraun", "gelbgrün", "gold", "goldrutengelb", "grau", "grün", "grüngelb", "hautfarben", "hellblau", "hellgrau", "hellgrün", "hellrosa", "himmelblau", "honigmelone", "indigo", "indischrot", "kastanie", "khaki", "königsblau", "korallenrot", "lachsfarben", "lavendel", "leinen", "lila", "limone", "limonengrün", "magenta", "mandelweiß", "minze", "mokassin", "muschel", "navajoweiß", "neon", "olivgrün", "orange", "orangerot", "papayacreme", "peru", "pfirsich", "pflaume", "rasengrün", "rauchfarben", "regenbogen", "rosa", "rot", "sandbraun", "schieferblau", "schiefergrau", "schneeweiß", "schwarz", "seegrün", "sienaerde", "silber", "stahlblau", "tomate", "transparent", "türkis", "veilchen", "violett", "weiß", "weizen");


  val categories: Map[String, Vector[String]] = Map(
    "automarke" -> automarke,
    "fluss" -> fluss,
    "land" -> land,
    "farbe" -> farbe)

  val r = scala.util.Random

  def getVector(category: String): Option[Vector[String]] = {
    try {
      categories.get(category)
    } catch {
      case e: Exception => None
    }
  }


  def solveGame(grid: GridInterface): Grid = {
    var solvedGrid = new GridCreator(grid.height, grid.width).createGrid()
    for (row <- 1 until grid.height; column <- 0 until grid.width) {
      val category: String = grid.cell(0, column).toString

      // depends on current letter
      val currentChar: String = Round.getCharacterForRound(row).toString
      println(currentChar)
      val allWordsForCategory: Vector[String] = categories(category)
      val allWordsForCategoryInUppercase: Vector[String] = for (upperCaseWord <- allWordsForCategory) yield upperCaseWord.toUpperCase()
      val wordsForCurrentChar: Vector[String] = allWordsForCategoryInUppercase.filter(_.startsWith(currentChar))

      val frequencyPerCategory: Int = wordsForCurrentChar.size

      if (frequencyPerCategory != 0) {
        val randomCategoryId: Int = r.nextInt(frequencyPerCategory)
        val randomCategoryAsString: String = wordsForCurrentChar(randomCategoryId)
        solvedGrid = solvedGrid.set(row, column, randomCategoryAsString)
      } else {
        solvedGrid = solvedGrid.set(row, column, "---")
      }


    }
    solvedGrid
  }

}
