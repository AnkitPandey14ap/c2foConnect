package com.example.c2foconnect.connectins


class Contact(val name: String, val lastMsg: String) {

    companion object {
        private var lastContactId = 0
        fun createContactsList(numContacts: Int): ArrayList<Contact> {
            val contacts = ArrayList<Contact>()
            for (i in 1..numContacts) {
                contacts.add(Contact("Person " + ++lastContactId, "msg: $i"))
            }
            return contacts
        }
    }
}