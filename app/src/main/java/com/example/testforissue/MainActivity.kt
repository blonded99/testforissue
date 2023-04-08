package com.example.testforissue


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.testforissue.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


class MainActivity : AppCompatActivity() {
//    lateinit var viewModel: MyViewModel
    lateinit var binding: ActivityMainBinding

//    private var list = ArrayList<String>() // post image 넘어오는 array
//
//    private val adapter = ViewPagerAdapter(list)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        viewModel = ViewModelProvider(this)[MyViewModel::class.java]

        list.add("R.drawable.helmet")
        list.add("R.drawable.helmet")

        binding.bodyImage.adapter = adapter
        binding.bodyImage.orientation = ViewPager2.ORIENTATION_HORIZONTAL


        binding.button.setOnClickListener {
            binding.bodyImage.adapter?.notifyDataSetChanged()
        }

         */

        binding.button.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val path = "/data/data/$packageName/cloth.png"
                    removeBackground(path)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    }

    suspend fun removeBackground(path: String){
        val file = File(path)

        val client = OkHttpClient().newBuilder().build()
        val requestBody: RequestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart(
                "file",
                file.name,
                file.asRequestBody("image/*".toMediaType())
            )
            .build()
        val request: Request = Request.Builder()
            .url("http://10.0.2.2:5000/seg_clothes".toHttpUrl())
            .method("POST", requestBody)
            .addHeader("Content-Type", "multipart/form-data")
            .build()
        val response: Response = client.newCall(request).execute()
        Log.e("","response= ${response}")
//        response.close()

        withContext(Dispatchers.Main) {
            // handle the response on the main thread
        }
    }

}