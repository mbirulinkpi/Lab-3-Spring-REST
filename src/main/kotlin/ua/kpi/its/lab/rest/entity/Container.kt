import ua.kpi.its.lab.rest.entity.Hospital
/**

An interface representing a container for storing objects of type T.
 */
interface Container<T> {
    /**

    Adds an element of type T to the container.
    @param element The element to be added to the container.
     */
    fun add(element: T)

    /**

    Removes an element from the container at the specified index.
    @param index The index of the element to be removed.
     */
    fun remove(index: Int)

    /**

    Updates an element in the container at the specified index with a new element of type T.
    @param index The index of the element to be updated.
    @param element The new element to replace the existing element at the specified index.
     */
    fun update(index: Int, element: T)

    /**

    Retrieves the element at the specified index from the container.
    @param index The index of the element to be retrieved.
    @return The element at the specified index.
     */
    fun get(index: Int): T

    /**

    Retrieves all elements from the container.
    @return A list of all elements in the container.
     */
    fun getAll(): List<T>
}

/**

A class representing a container for storing Hospital objects.
 */
class HospitalContainer : Container<Hospital> {
    private val hospitals: MutableList<Hospital> = mutableListOf()

    /**

    Adds a Hospital object to the container.
    @param element The Hospital object to be added to the container.
     */
    override fun add(element: Hospital) {
        hospitals.add(element)
    }

    /**

    Removes a Hospital object from the container at the specified index.
    @param index The index of the Hospital object to be removed.
     */
    override fun remove(index: Int) {
        hospitals.removeAt(index)
    }

    /**

    Updates a Hospital object in the container at the specified index with a new Hospital object.
    @param index The index of the Hospital object to be updated.
    @param element The new Hospital object to replace the existing Hospital object at the specified index.
     */
    override fun update(index: Int, element: Hospital) {
        hospitals[index] = element
    }

    /**

    Retrieves a Hospital object from the container at the specified index.
    @param index The index of the Hospital object to be retrieved.
    @return The Hospital object at the specified index.
     */
    override fun get(index: Int): Hospital {
        return hospitals[index]
    }

    /**

    Retrieves all Hospital objects from the container.
    @return A list of all Hospital objects in the container.
     */
    override fun getAll(): List<Hospital> {
        return hospitals
    }
}