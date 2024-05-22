package id.ac.istts.myfit.ChatBot

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.BlockThreshold
import com.google.ai.client.generativeai.type.HarmCategory
import com.google.ai.client.generativeai.type.SafetySetting
import com.google.ai.client.generativeai.type.ServerException
import com.google.ai.client.generativeai.type.generationConfig
import id.ac.istts.myfit.Data.ChatBot
import id.ac.istts.myfit.databinding.FragmentMenuChatBotInProfileBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Menu_chat_bot_in_profile : Fragment() {

    private lateinit var binding: FragmentMenuChatBotInProfileBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    val ioScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    val mainScope = CoroutineScope(Dispatchers.Main)

//    var chatBotList = mutableListOf<String>("temp1","temp2","temp3","temp4","temp5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuChatBotInProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.rvChatBot
        recyclerView.adapter = ChatBotAdapter(data = chatBotList)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        layoutManager.stackFromEnd = true
        recyclerView.layoutManager = layoutManager

        val etChat = binding.sendmsgchat
        val btnSend = binding.btnsendrequest

        // Send Message
        if (chatBotList.isEmpty()){
            val id = 1
            chatBotList.add(ChatBot(id= id,side = "bot", message = "Hello, I am Mifit AI. How can I help you today?"))
            recyclerView.adapter?.notifyItemInserted(chatBotList.size)
        }


        btnSend.setOnClickListener {
            val message = etChat.text.toString()
            if (message.isNotEmpty()) {
                val id = chatBotList.last().id + 1
                chatBotList.add(ChatBot(id= id,side = "user", message = message))
                recyclerView.adapter?.notifyItemInserted(chatBotList.size)
            }
            etChat.text.clear()

            // Bot Response
            val model = GenerativeModel(
                "gemini-1.5-flash-latest",
                // Retrieve API key as an environmental variable defined in a Build Configuration
                // see https://github.com/google/secrets-gradle-plugin for further instructions
                "AIzaSyC4E6SD5wvfOlUB72H5vGlufHl6MZc_YXo",
                generationConfig = generationConfig {
                    temperature = 1f
                    topK = 64
                    topP = 0.95f
                    maxOutputTokens = 8192
                    responseMimeType = "text/plain"
                },
                safetySettings = listOf(
                    SafetySetting(HarmCategory.HARASSMENT, BlockThreshold.MEDIUM_AND_ABOVE),
                    SafetySetting(HarmCategory.HATE_SPEECH, BlockThreshold.MEDIUM_AND_ABOVE),
                    SafetySetting(HarmCategory.SEXUALLY_EXPLICIT, BlockThreshold.MEDIUM_AND_ABOVE),
                    SafetySetting(HarmCategory.DANGEROUS_CONTENT, BlockThreshold.MEDIUM_AND_ABOVE),
                ),
            )

            val chat = model.startChat()

            ioScope.launch {

                var response = ""
                try {
                    response = chat.sendMessage(message).text.toString()
                } catch (e: ServerException) {
                    // Handle the exception by retrying or reporting the issue
                    Log.e("GenerativeAI", "An internal error has occurred: ${e.message}")
                    response = "Sorry, I am having trouble processing your request. Please try again later."
                    // Retry the operation or report the issue to Google
                } catch (e:Exception){
                    Log.e("GenerativeAI", "An internal error has occurred: ${e.message}")
                    response = "Sorry, I am having trouble processing your request. Please try again later."
                }

                mainScope.launch {
                    val id = chatBotList.last().id + 1
                    chatBotList.add(ChatBot(id= id,side = "bot", message = response))
                    recyclerView.adapter?.notifyItemInserted(chatBotList.size)
                }
            }

            recyclerView.smoothScrollToPosition(chatBotList.size - 1)
        }

    }

    companion object{
        var chatBotList = mutableListOf<ChatBot>()
    }
}