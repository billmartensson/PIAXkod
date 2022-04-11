package se.magictechnology.piaxkod

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log


class Person
{
    var name = ""
    var bornYear = 0
    var father : Person? = null
    var mother : Person? = null

    fun printParents()
    {
        if(father == null)
        {
            return
        }
        father!!.printParents()
        mother!!.printParents()
        Log.i("PIAXDEBUG", "Namn: " + name)
        Log.i("PIAXDEBUG", "Pappa: " + father!!.name + " Mamma: " + mother!!.name)
    }

    fun getOldest() : Int
    {
        Log.i("PIAXDEBUG", "Nu kolla ålder på " + name)
        if(father == null)
        {
            return bornYear
        }
        var motherBorn = mother!!.getOldest()
        var fatherBorn = father!!.getOldest()

        if(motherBorn < fatherBorn)
        {
            return motherBorn
        } else {
            return fatherBorn
        }
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //secretsanta()
        //shorterText("Detta här är en lång text som ska bli kortare", 19)
        //shorterText("Hej", 19)
        //shorterText("Detta här är en lång text som ska bli kortare", 17)
        //boxText("Jag gillar pannkakor med sylt utan sojabetorskvist")

        familyTree()
    }


    fun familyTree()
    {
        /*

8Helge  9Inga
  \   /
    4David      5Erika     6Fredrik    7Gertrud
          \     /           \     /
            3Casear          2Berit
                  \      /
                    1Arne
         */

        var p1 = Person()
        p1.name = "Arne"
        var p2 = Person()
        p2.name = "Berit"
        var p3 = Person()
        p3.name = "Casear"
        var p4 = Person()
        p4.name = "David"
        var p5 = Person()
        p5.name = "Erika"
        var p6 = Person()
        p6.name = "Fredrik"
        var p7 = Person()
        p7.name = "Gertrud"
        var p8 = Person()
        p8.name = "Helge"
        var p9 = Person()
        p9.name = "Inga"

        p1.bornYear = 2000
        p2.bornYear = 1970
        p3.bornYear = 1972
        p4.bornYear = 1940
        p5.bornYear = 1942
        p6.bornYear = 1939
        p7.bornYear = 1945
        p8.bornYear = 1915
        p9.bornYear = 1903


        p1.father = p3
        p1.mother = p2

        p2.father = p6
        p2.mother = p7

        p3.father = p4
        p3.mother = p5

        p4.father = p8
        p4.mother = p9

        p1.printParents()

        Log.i("PIAXDEBUG", p1.getOldest().toString())
    }


    fun boxText(thetext : String)
    {
        /*
        Jag gillar pannkakor

        *************
        * Jag       *
        * gillar    *
        * pannkakor *
        * med       *
        * sylt      *
        *************

         */

        var thewords = thetext.split(" ")

        var longestWord = 0
        for (word in thewords) {
            if(word.length > longestWord)
            {
                longestWord = word.length
            }
        }

        Log.i("PIAXDEBUG", longestWord.toString())

        var starline = ""
        repeat(longestWord+4) {
            starline = starline + "*"
        }

        Log.i("PIAXDEBUG", starline)

        for(word in thewords)
        {
            var spaceString = ""
            repeat(longestWord-word.length) {
                spaceString = spaceString + " "
            }

            Log.i("PIAXDEBUG", "* " + word + spaceString + " *")
        }
        Log.i("PIAXDEBUG", starline)

    }

    fun shorterText(thetext : String, maxchars : Int)
    {
        /*
        Detta här är en lång text som ska bli kortare
        Detta här är en...
         */

        //var shortText = thetext.substringBeforeLast(" ")
        var cutToIndex = maxchars
        if(cutToIndex > thetext.length)
        {
            cutToIndex = thetext.length
        }
        var shortText = thetext.substring(0,cutToIndex)
        shortText = shortText.substringBeforeLast(" ")
        if(shortText.length+3 > maxchars)
        {
            shortText = shortText.substringBeforeLast(" ")
        }
        shortText = shortText + "..."

        Log.i("PIAXDEBUG", shortText.length.toString() + " " + shortText)
    }


    fun secretsanta()
    {
        /*
        David
        Arne
        Erik
        Casear
        Fredrik
        Bengt
         */

        var people = mutableListOf<String>()
        people.add("Arne")      // 0
        people.add("Bengt")      // 1
        people.add("Casear")      // 2
        people.add("David")      // 3
        people.add("Erik")      // 4
        people.add("Fredrik")      // 5

        people.shuffle()

        /*
        for(person in people)
        {
            Log.i("PIAXDEBUG", person)
        }
        */
        people.forEachIndexed { index, person ->
            //Log.i("PIAXDEBUG", index.toString() + " " + person)

            //Log.i("PIAXDEBUG", index.toString() + " " + (index % 2).toString())

            var giveToIndex = index + 1
            if(giveToIndex == people.size)
            {
                giveToIndex = 0
            }

            if(index % 2 == 0)
            {
                Log.i("PIAXDEBUG", "* " + people[index] + " ska ge till " + people[giveToIndex])
            } else {
                Log.i("PIAXDEBUG", "+ " + people[index] + " ska ge till " + people[giveToIndex])
            }
        }
    }


}