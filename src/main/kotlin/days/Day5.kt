package days

class Day5 : Day(5) {

    val numberRegex = Regex("\\d+")
    val seedRegex = "(?<=seeds:).*?(?=\\n{2})".toRegex(RegexOption.DOT_MATCHES_ALL)
    val seedToSoilRegex = "(?<=seed-to-soil map:\\n).*?(?=\\n{2})".toRegex(RegexOption.DOT_MATCHES_ALL)
    val soilToFertilizerRegex = "(?<=soil-to-fertilizer map:\\n).*?(?=\\n{2})".toRegex(RegexOption.DOT_MATCHES_ALL)
    val fertilizerToWaterRegex = "(?<=fertilizer-to-water map:\\n).*?(?=\\n{2})".toRegex(RegexOption.DOT_MATCHES_ALL)
    val waterToLightRegex = "(?<=water-to-light map:\\n).*?(?=\\n{2})".toRegex(RegexOption.DOT_MATCHES_ALL)
    val lightToTemperatureRegex = "(?<=light-to-temperature map:\\n).*?(?=\\n{2})".toRegex(RegexOption.DOT_MATCHES_ALL)
    val temperatureToHumidityRegex = "(?<=temperature-to-humidity map:\\n).*?(?=\\n{2})".toRegex(RegexOption.DOT_MATCHES_ALL)
    val humidityToLocationRegex = "(?<=humidity-to-location map:\\n).*".toRegex(RegexOption.DOT_MATCHES_ALL)

    override fun partOne(): Any {
        var seeds = seeds()
//        println(seeds.map{ it })
        var seedToSoil = createMapRange(seedToSoilRegex)
        var soilToFertilizer = createMapRange(soilToFertilizerRegex)
        var fertilizerToWater = createMapRange(fertilizerToWaterRegex)
        var waterToLight = createMapRange(waterToLightRegex)
        var lightToTemperature = createMapRange(lightToTemperatureRegex)
        var temperatureToHumidity = createMapRange(temperatureToHumidityRegex)
        var humidityToLocation = createMapRange(humidityToLocationRegex)

        return seeds
            .map { seedToSoil.getDestination(it) }
            .map { soilToFertilizer.getDestination(it) }
            .map { fertilizerToWater.getDestination(it) }
            .map { waterToLight.getDestination(it) }
            .map { lightToTemperature.getDestination(it) }
            .map { temperatureToHumidity.getDestination(it) }
            .map { humidityToLocation.getDestination(it) }
            .min()
    }

    override fun partTwo(): Any {
        return ""
    }

    private fun seeds(): List<Long> {
        return numberRegex
            .findAll(seedRegex.findAll(inputString).first().value)
            .map{ it.value.toLong() }
            .toList()
    }

    private fun createMapRange(reg: Regex): MapRange {
        val numbers = numberRegex
            .findAll(reg.findAll(inputString).first().value)
            .map{ it.value.toLong() }
            .toList()

        return MapRange(numbers)
    }

    data class MapRange(val numbers : List<Long>) {

        val items: MutableList<MapRangeItem> = mutableListOf()

        init {
            for(i in 0..numbers.count()-1 step 3) {
                add(
                    MapRangeItem(
                        LongRange(numbers[i], numbers[i]+numbers[i+2]-1),
                        LongRange(numbers[i+1], numbers[i+1]+numbers[i+2]-1)
                    )
                )
            }
        }

        fun add(item: MapRangeItem) {
            items.add(item)
        }

        fun getDestination(source: Long): Long {
            var item: MapRangeItem? = items.find{ it.source.contains(source) }
            if (item == null) {
                return source
            }
            var index = item.source.indexOf(source)
            return item.destination.elementAt(index)
        }
    }

    data class MapRangeItem(val destination: LongRange, val source: LongRange) {}
}