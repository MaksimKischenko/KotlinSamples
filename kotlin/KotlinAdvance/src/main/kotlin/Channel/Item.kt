package Channel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay

data class Item(val number: Int) {
    init {
        println("Item was made: ${this.hashCode()}")
    }
}


suspend fun getItems(channel: Channel<Item>) {
    channel.send(makeItem(1))
    channel.send(makeItem(2))
    channel.send(makeItem(3))
    channel.send(makeItem(4))
    channel.close()
}

suspend fun makeItem(number: Int): Item {
    delay(100)
    return Item(number)
}

suspend fun consumeItems(channel: Channel<Item>) {
    for (item in channel) {
        println("Do something with ${item.hashCode()}")
    }
}