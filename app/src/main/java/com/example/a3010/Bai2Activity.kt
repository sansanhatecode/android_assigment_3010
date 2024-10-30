package com.example.a3010

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Bai2Activity : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var studentList: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b2)

        // Khởi tạo danh sách sinh viên
        studentList = listOf(
            Student("Hoang Duc Gia Hung", "20215062"),
            Student("Van Dieu Linh", "20250107"),
            Student("Hoang Ngoc Tran", "20072123"),
            Student("Nguyen Van A", "123456"),
            Student("Le Thi B", "654321"),
            Student("Tran Van C", "111222"),
            Student("Pham Thi D", "333444"),
            Student("Hoang Van E", "555666"),
            Student("Nguyen Thi F", "777888"),
            Student("Bui Van G", "999000"),
            Student("Dang Thi H", "112233"),
            Student("Do Van I", "223344"),
            Student("Ngo Thi J", "334455"),
            Student("Pham Van K", "445566"),
            Student("Trinh Thi L", "556677"),
            Student("Vu Van M", "667788"),
            Student("Nguyen Van N", "778899"),
            Student("Pham Thi O", "889900"),
            Student("Tran Van P", "990011"),
            Student("Nguyen Thi Q", "101112"),
            Student("Dang Van R", "121314"),
            Student("Nguyen Van S", "141516"),
            Student("Le Thi T", "161718"),
            Student("Nguyen Van U", "171819"),
            Student("Pham Van V", "181920"),
            Student("Hoang Thi W", "192021"),
            Student("Bui Van X", "202122"),
            Student("Dang Thi Y", "212223"),
            Student("Do Van Z", "222324")
        )

        studentAdapter = StudentAdapter(studentList)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        // Xử lý tìm kiếm
        val etSearch = findViewById<EditText>(R.id.etSearch)
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val keyword = s.toString().trim()
                if (keyword.length > 2) {
                    val filteredList = studentList.filter {
                        it.name.contains(keyword, ignoreCase = true) || it.mssv.contains(keyword)
                    }
                    studentAdapter.updateList(filteredList)
                } else {
                    studentAdapter.updateList(studentList)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}
