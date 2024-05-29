package Flow

import kotlinx.coroutines.flow.*

sealed class Weapon {
  class Sword(): Weapon()
  class Staff(): Weapon()
  class SpellBook(): Weapon()
}


private suspend fun main()  {
  val weaponProducer: Flow<Weapon> = flowOf()
  val weaponBox = mutableListOf<Weapon>()

  weaponProducer.collect { weapon ->
    weaponBox.add(weapon)
  }

  weaponProducer.filter { weapon ->
      weapon is Weapon.Sword
    }.collect{}

  //So if we wanted each Weapon to be different from
  // its predecessor we would do it like this:
  weaponProducer
    .distinctUntilChanged()
    .collect{}


  weaponProducer
    .map { weapon ->
      mapToEnchantedWeapon(weapon)
    }.collect{}


  //Map transforms each element individually while f
  // latMap transforms each element into another Flow
  // and then merges those two flows into one.

 
  //Zip
  //Zip combines two flows together, it takes corresponding
// elements from each flow and pairs them into a single element.
// This happens sequentially: the first element of Flow
// A is paired with the first element of Flow B,
// the second with the second, and so on.

}

fun mapToEnchantedWeapon(weapon: Weapon) {

}
