package com.example.warrungu.components.dictionary
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.warrungu.R
import com.example.warrungu.components.cards.Card
import com.example.warrungu.components.cards.CardAdapter
import com.example.warrungu.components.cards.CardClickListener
import com.example.warrungu.databinding.ActivityDictionaryByTypeBinding
import com.example.warrungu.room.DICT_ID_EXTRA
import com.example.warrungu.room.DictionaryDatabase

//import kotlinx.android.synthetic.main.activity_main.*

//private lateinit var searchButton : TextView

class DictionaryByType : AppCompatActivity(), CardClickListener {
    private lateinit var dictionaryDatabase: DictionaryDatabase
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var cardArrayList: ArrayList<Card>
    private lateinit var binding: ActivityDictionaryByTypeBinding
    companion object {
        const val WORD_TYPE = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDictionaryByTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val wordType = intent.getStringExtra(WORD_TYPE)
        val titleTxt = findViewById<TextView>(R.id.title)
        titleTxt.text = wordType.toString()
        Log.d("EXTRAS",wordType.toString())

        val searchButton = findViewById<Button>(R.id.searchButton)
        searchButton.setOnClickListener {
            submitSearch()
        }
        submitSearch()
    }
    private fun getData(search: String){

        dictionaryDatabase = DictionaryDatabase.getInstance(applicationContext)
        var rows: List<com.example.warrungu.room.DictionaryItem>
        cardArrayList =  arrayListOf<Card>()

        val wordType = intent.getStringExtra(WORD_TYPE)

        if (search.isBlank()){
            rows = dictionaryDatabase.dao.getDictionaryItemsOfType(wordType.toString())
        }else{
            rows = dictionaryDatabase.dao.getDictionaryItemsOfTypeSearch(wordType.toString(),"%" + search + "%")
        }

        for(row in rows){
            val card = Card(row.english.toString(), row.language.toString(),row.id.toString())
            cardArrayList.add(card)
        }
    }
    fun submitSearch(){
        val searchTxt = findViewById<EditText>(R.id.searchEditText)
        getData(searchTxt.text.toString())

        binding.searchRecyclerView.apply{
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = CardAdapter(cardArrayList)
        }
    }

    override fun onClick(card: Card) {
        val intent = Intent(applicationContext, DictionaryItem::class.java)
        intent.putExtra(DICT_ID_EXTRA, card.id)
        startActivity(intent)
    }
}

