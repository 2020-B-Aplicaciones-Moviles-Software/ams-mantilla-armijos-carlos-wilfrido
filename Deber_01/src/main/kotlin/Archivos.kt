import java.io.File
import java.nio.file.Files
import java.nio.file.StandardOpenOption

class Archivos {

    companion object {
        val urlProd = "src/main/resources/Productoras"
        val urlVid = "src/main/resources/Videojuegos"
        val fileProd = File(urlProd)
        val fileVid = File(urlVid)
        var outString = ""

        fun leerProductoras(): ArrayList<String> {
            val productoras = arrayListOf<String>()
            fileProd.useLines { lines -> lines.forEach { productoras.add(it) } }
            return productoras
        }

        fun leerVideojuegos(): ArrayList<String> {
            val videojuegos = arrayListOf<String>()
            fileVid.useLines { lines -> lines.forEach { videojuegos.add(it) } }
            return videojuegos
        }

        fun escribirProductora(productora: Productora){
            outString = productora.archivo()+"\n"
            Files.write(fileProd.toPath(), outString.toByteArray(), StandardOpenOption.TRUNCATE_EXISTING)
            outString=""
        }
        fun escribirProductoraLista(productoras: MutableList<Productora>){
            Files.write(fileProd.toPath(), outString.toByteArray(), StandardOpenOption.TRUNCATE_EXISTING)
            for (i in 0 until productoras.size) {
                outString = outString+productoras[i].archivo() + "\n"
            }
            Files.write(fileProd.toPath(), outString.toByteArray(), StandardOpenOption.APPEND)
            outString=""
        }


        fun escribirVideojuego(videojuego: Videojuego){
            outString = videojuego.toString()+"\n"
            Files.write(fileVid.toPath(), outString.toByteArray(), StandardOpenOption.APPEND)
            outString=""
        }
        fun escribirVideojuegoLista(videojuegos: MutableList<Videojuego>){
            Files.write(fileVid.toPath(), outString.toByteArray(), StandardOpenOption.TRUNCATE_EXISTING)
            for (i in 0 until videojuegos.size) {
                outString = outString+videojuegos[i].toString() + "\n"
            }
            Files.write(fileVid.toPath(), outString.toByteArray(), StandardOpenOption.APPEND)
            outString=""
        }
    }
}