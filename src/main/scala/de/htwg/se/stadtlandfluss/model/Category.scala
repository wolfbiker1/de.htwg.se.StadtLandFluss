package de.htwg.se.stadtlandfluss.model

case class Category(size:Int){
    def createRandom(num: Int): Grid = {
        var grid = new Grid(size)
        for {index <- 1 to num} {
            grid = setRandomcategory(grid)
        }
        grid
    }

    def setRandomcategory(grid: Grid): Grid ={
        val headline: Vector[Vector[String]] = Vector(land,fluss, automarken,farben);
        for (i <- 0 until headline.length){
§
        }
    }

    /*
    for( categories.rows <- 0 to grid.size)
        {
          for( category.cell <- 0 to category.rows)
        {
        * if (grid.cell = 1)
            category.cell(1,): Vector[String] = land
           if (grid.rows = 2)
               category.cell(1,grid.cell): Vector[String] = fluss
            *
            if (grid.rows = 1)
            grid.cell(1,grid.cell): Vector[String] = automarken
            if (grid.rows = 1)
            grid.cell(1,grid.cell): Vector[String] = farben
        }
       }
    *
    * */

    /*
   * for( grid.rows <- 0 to grid.size)
       {
         for( grid.cell <- 0 to grid.rows)
       {
       * if (grid.rows = 1)
           grid.cell(1,grid.cell): Vector[String] = land
          if (grid.rows = 2)
           grid.cell(1,grid.cell): Vector[String] = fluss
           *
           if (grid.rows = 1)
           grid.cell(1,grid.cell): Vector[String] = automarken
           if (grid.rows = 1)
           grid.cell(1,grid.cell): Vector[String] = farben
       }
      }
   *
   * */
       val land = Vector("Afghanistan","Ägypten", "Albanien", "Algerien", "Andorra", "Angola", "Antigua und Barbuda", "Äquatorialguinea", "Argentinien", "Armenien", "Aserbaidschan", "Äthiopien",
    "Australien","Bahamas" , "Bahrain", "Bangladesch","Barbados", "Belgien", "Belize","Benin", "Bhutan", "Bolivien", "Bosnien und Herzegowina", "Botsuana", "Brasilien", "Brunei Darussalam", "Bulgarien", "Burkina Faso", "Burundi", "Chile", "China", "Cookinseln", "Costa Rica", "Côte d'Ivoire", "Dänemark", "Deutschland", "Dominica", "Dominikanische Republik", "Dschibuti", "Ecuador", "El Salvador", "Eritrea", "Estland", "Fidschi", "Finnland", "Frankreich", "Gabun", "Gambia", "Georgien","Ghana",
    "Grenada", "Griechenland", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Indien", "Indonesien", "Irak",
    "Iran","Irland","Island","Israel","Italien","Jamaika","Japan","Jemen","Jordanien","Kambodscha","Kamerun","Kanada","Kap Verde", "Kasachstan", "Katar","Kenia", "Kirgisistan",
    "Kiribati","Kolumbien","Komoren", "Kongo","Kongo Demokratische Republik","Korea Demokratische Volksrepublik", "Korea Republik","Kosovo","Kroatien","Kuba","Kuwait","Laos","Lesotho","Lettland",
    "Libanon","Liberia","Libyen","Liechtenstein","Litauen","Luxemburg","Madagaskar","Malawi","Malaysia","Malediven","Mali", "Malta",
    "Marokko","Marshallinseln","Mauretanien","Mauritius", "Mazedonien","Mexiko","Mikronesien","Moldau","Monaco","Mongolei","Montenegro","Mosambik","Myanmar","Namibia", "Nauru",
    "Nepal","Neuseeland","Nicaragua","Niederlande","Niger","Nigeria","Niue","Norwegen","Oman","Österreich","Pakistan","Palau","Panama","Papua-Neuguinea",
    "Paraguay","Peru","Philippinen","Polen","Portugal","Ruanda","Rumänien","Russische Föderation","alomonen","Sambia","Samoa","San Marino",
    "Tomé und Príncipe","Saudi-Arabien","Schweden", "Schweiz","Senegal","Serbien","Seychellen","Sierra Leone","Simbabwe","Singapur","Slowakei","Slowenien","Somalia","Spanien",
    "Sri Lanka","St. Kitts und Nevis","St. Lucia","St. Vincent und die Grenadinen","Südafrika","Sudan","Südsudan","Suriname","Swasiland","Syrien","Tadschikistan","Tansania","Thailand","Timor-Leste","Togo","Tonga","Trinidad und Tobago","Tschad","Tschechische Republik","Tunesien","Türkei","Turkmenistan","Tuvalu","Uganda","Ukraine","Ungarn","Uruguay","Usbekistan","Vanuatu","Vatikanstadt","Venezuela","Vereinigte Arabische Emirate",
    "Vereinigte Staaten", "Vereinigtes Königreich", "Vietnam", "Weißrussland", "Zentralafrikanische Republik", "Zypern");

    val fluss = Vector( "Ager", "Aist", "Alm", "Bregenzerach", "Donau", "Dornbirner Ach", "Drau", "Enns", "Erlauf", "Feistritz", "Fischa", "Gail", "Gasteiner Ache", "Glan", "Gürtschitz", "Grossache", "Grosse Mhül", "Gurk", "Ill", "Inn", "Isel", "Kainach", "Kamp", "Krems", "Lafnitz", "Lavant", "Lech", "Leinsitz", "Leitha", "Lieser", "Liesingbach", "Malta", "March", "Mur", "Müll", "März", "Ötztaler Ache", "Palten", "Pielach", "Piesting", "Pinka", "Pitze",  "Raab", "Rhein", "Saalach", "Salza", "Salzach", "Schwarza", "Schwechat", "Seeache", "Sill", "Sulm", "Steyr", "Taurach", "Thaya", "Traisen", "Traun", "Triesting", "Wien", "Ysper", "Zederhausbach", "Ziller");
    val automarken = Vector("Acura ", "Aixam ", "Alfa Romeo ", "Ariel ", "Aston Martin ", "Audi ", "Bentley ", "Bitter ", "Brilliance ", "Bristol ", "Bugatti ", "Buick ", "BMW ", "Cadilla ", "Callaway ", "Caterhem ", "Chevrolet ", "CityEL ", "Chrysler ", "Citroen ", "Commuter Cars ", "Dacia ", "Daewoo ", "Daihatsu ", "De Tomaso ", "Dodge ", "Donkervoort ", "Ferarri ", "Fiat ", "Fisker ", "Ford ", "GAS ", "Ginetta ", "GMC ", "Gumpert ", "Heuliez ", "Holden ", "Honda ", "Hummer ", "Hyundai ", "Infiniti ", "Iran Khodro ", "Isdera ", "Isuzu ", "Jaguar ", "Jeep ", "Karmann ", "Kia ", "Koenigsegg ", "Lada ", "Lamborghini ", "Lancia ", "Land-Rover ", "Lexus ", "Ligier ", "Lincoln ", "Lola ", "Lotus ", "LTI ", "Mahindra ", "Marcos ", "Maruti ", "Maserati ", "Matra ", "Maybach ", "Mazda ", "McLaren ", "Mercedes-Benz ", "Merlin ", "MG ", "Microcar ", "Mini ", "Mitsubishi ", "Mitsuoka ", "Morgan ", "Moskwitsch ", "Nanjing ", "Nissan ", "Oldsmobile ", "Opel ", "Pagani ", "Panoz ", "Peugeot ", "Pininfarina ", "Pontiac ", "Porsche ", "Proton ", "Radical ", "Reliant ", "Renault ", "Rinspeed ", "Rolls-Royce ", "Rover ", "Saab ", "Saleen ", "SAS ", "Scion ", "Seat ", "Skoda ", "Smart ", "Spyker ", "SsangYong ", "Subaru ", "Suzuki ", "Tata ", "Tatra ", "Toyota ", "TVR ", "Twike ", "Vauxhall ", "Volvo ", "VW-Volkswagen ", "Wiesmann ", "Zagato ", "ZAP ", "Zastava ", "Zender");
//Stadt zu gross
    val farben = Vector("ufeffaltrosa", "aquamarinblau", "beige", "biskuit", "blassgrün", "blasstürkis", "blau", "blauviolett", "braun", "bunt", "chiffongelb", "creme", "cyan", "distel", "dunkelblau", "dunkelgrün", "dunkeltürkis", "elfenbein", "frühlingsgrün", "gelb", "gelbbraun", "gelbgrün", "gold", "goldrutengelb", "grau", "grün", "grüngelb", "hautfarben", "hellblau", "hellgrau", "hellgrün", "hellrosa", "himmelblau", "honigmelone", "indigo", "indischrot", "kastanie", "khaki", "königsblau", "korallenrot", "lachsfarben", "lavendel", "leinen", "lila", "limone", "limonengrün", "magenta", "mandelweiß", "minze", "mokassin", "muschel", "navajoweiß", "neon", "olivgrün", "orange", "orangerot", "papayacreme", "peru", "pfirsich", "pflaume", "rasengrün", "rauchfarben", "regenbogen", "rosa", "rot", "sandbraun", "schieferblau", "schiefergrau", "schneeweiß", "schwarz", "seegrün", "sienaerde", "silber", "stahlblau", "tomate", "transparent", "türkis", "veilchen", "violett", "weiß", "weizen");


}