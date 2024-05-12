package id.ac.istts.myfit.Data.Source.Mock

import id.ac.istts.myfit.Data.Menu

object MenuUserMockDB {
    private val allMenuUser:ArrayList<Menu> = ArrayList()
    fun getAllMenuUser(force:Boolean = false):ArrayList<Menu>{
        return ArrayList(allMenuUser)
    }
    fun getMenuById(id:Int):Menu?{
        return allMenuUser.find {
            it.id == id
        }
    }
    fun createMenu(menu:Menu){
        allMenuUser.add(menu)
    }
//    fun updateMenu(menu:Menu){
//        val p = allMenuUser.find {
//            it.id == menu.id
//        }
//        p?.title = post.title
//        p?.content = post.content
//    }
//    fun deletePost(post:Post){
//        val idx = posts.indexOfFirst {
//            it.id == post.id
//        }
//        if(idx >= 0){
//            posts.removeAt(idx)
//        }
//    }
}