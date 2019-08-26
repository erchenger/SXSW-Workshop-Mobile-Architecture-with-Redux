package com.elliott.sxswreduxworkshopandroid.network.model

data class RootModel(val collection: ImageCollection)
data class ImageCollection(val href: String, val items: List<ImageCollectionItem>){
    override fun toString(): String {
        return "Number of Items: ${items.size}"
    }
}
data class ImageCollectionItem(
    val data: List<ImageCollectionItemData>,
    val href: String,
    val links: List<ImageCollectionLink>
)
data class ImageCollectionItemData(val description: String?, val title: String)
data class ImageCollectionLink(val href: String)
