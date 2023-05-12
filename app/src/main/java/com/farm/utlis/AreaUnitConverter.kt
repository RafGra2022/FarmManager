package com.farm.utlis

class AreaUnitConverter {

    companion object {
        fun unitToSquareMeter(unit: String) : Float{
            when(unit) {
                "m2" -> return 1f
                "ar" -> return 100f
                "ha" -> return 10000f
            }
            return 0f
        }

        fun squareMeterToHectare(area : Float) : Float{
            return area/10000
        }
    }
}