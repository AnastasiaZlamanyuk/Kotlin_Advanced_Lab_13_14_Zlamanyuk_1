class Library<T> {
    val resources = mutableListOf<T>()
}

data class Book(
    val title: String,
    val author: String,
    val year: Int,
    val isbn: String
)

data class Magazine(
    val title: String,
    val issue: Int,
    val month: String
)

data class DVD(
    val title: String,
    val director: String,
    val duration: Int
)

sealed class LibraryItem {
    data class BookItem(val book: Book) : LibraryItem()
    data class MagazineItem(val magazine: Magazine) : LibraryItem()
    data class DVDItem(val dvd: DVD) : LibraryItem()
}

fun filterByYear(book: List<Book>, year: Int): List<Book> {
    return book.filter { it.year == year }
}

fun sortByTitle(library: List<LibraryItem>): List<LibraryItem> {
    return library.sortedBy { r ->
        when (r) {
            is LibraryItem.DVDItem -> r.dvd.title
            is LibraryItem.BookItem -> r.book.title
            is LibraryItem.MagazineItem -> r.magazine.title
        }
    }
}

fun groupByAuthor(book: List<Book>): Map<String, List<Book>> {
    return book.groupBy { it.author }
}

fun calculateTotalDuration(dvdItems: List<LibraryItem.DVDItem>): Int {
    return dvdItems.sumOf { it.dvd.duration }
}

fun main() {
    val book1 = Book("Властелин колец", "Толкин", 1954, "978-5-17-154201-6")
    val book2 = Book("Гарри Поттер и философский камень", "Роулинг", 1997, "978-5-5353-00308")

    val magazine1 = Magazine("Time", 5, "May")
    val magazine2 = Magazine("Vogue", 4, "April")

    val dvd1 = DVD("Форсаж", "Роб Коэн", 106)
    val dvd2 = DVD("Титаник", "Джеймс Кэмерон", 195)

    val bl1 = LibraryItem.BookItem(book1)
    val bl2 = LibraryItem.BookItem(book2)

    val ml = LibraryItem.MagazineItem(m)

    val dl1 = LibraryItem.DVDItem(dvd1)
    val dl2 = LibraryItem.DVDItem(dvd2)

    val library = Library<LibraryItem>()
    library.resources.add(bl1)
    library.resources.add(bl2)
    library.resources.add(ml)
    library.resources.add(dl1)
    library.resources.add(dl2)

    println("Книги выпущенные в 1954 году")
    val b54 = filterByYear(listOf(book1, book2), 1954)
    b1866.forEach { println("${it.title} — ${it.author}") }

    println("Сортировка по названию")
    val sortedI = sortByTitle(library.resources)
    sortedI.forEach { println(it) }

    println("Сортировка книг по авторам")
    val groupedB = groupByAuthor(listOf(book1, book2))
    groupedB.forEach { (author, books) ->
        println("$author:")
        books.forEach { b -> println("${b.title} (${b.year})") }
    }

    println("Общая продолжительность (в минутах) DVD")
    val duration = calculateTotalDuration(library.resources.filterIsInstance<LibraryItem.DVDItem>())
    println("$duration минут.")
}