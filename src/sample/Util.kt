package sample

import sample.Models.Toy
import java.text.SimpleDateFormat
import java.util.*

class Util{
    companion object{
        fun printToyObject(toy:Toy):String{
            val sb =StringBuilder()
            if(toy.customMessage!=null){
                sb.append(toy.customMessage)
            }
            if(toy.name!=null){
                sb.append("Toy Name : ${toy.name}")
            }
            if(toy.code!=null){
                sb.append(" ,Toy Code : ${toy.code}")
            }
            if(toy.description!=null){
                sb.append(" ,Toy Description : ${toy.description}")
            }
            if(toy.price!=0){
                sb.append(" ,Toy Price : ${toy.price}")
            }
            if(toy.dom!=null){
                sb.append(" ,Toy Manufacture Date : ${toy.dom}")
            }
            if(toy.toyManfacturer!=null){
                if(toy.toyManfacturer.companyName!=null){
                    sb.append(" ,Toy Manufacture Company : ${toy.toyManfacturer.companyName}")
                }
                if(toy.toyManfacturer.country!=null){
                    sb.append(" ,Toy Manufacture Country : ${toy.toyManfacturer.country}")

                }
                if(toy.toyManfacturer.zipCode!=null){
                    sb.append(" ,Toy Manufacture ZipCode : +${toy.toyManfacturer.zipCode}")
                }
                if(toy.toyManfacturer.streetAddress!=null){
                    sb.append(" ,Toy Manufacture Address : P.O. BOX ${toy.toyManfacturer.streetAddress}")
                }

            }
            return sb.toString()
        }
        fun getCurrentDateTime():String{
            val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
            val date = Date();
            return dateFormat.format(date)

        }


    }
}