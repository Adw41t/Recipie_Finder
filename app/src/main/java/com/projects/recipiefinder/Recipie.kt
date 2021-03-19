package com.projects.recipiefinder

class Recipie {
    var image:String?=null
    var title:String?=null
    var link:String?=null
    var ingredients:String?=null

    constructor(title:String,link:String,ing:String,img:String){
        this.title=title
        this.link=link
        ingredients=ing
        image=img
    }
}