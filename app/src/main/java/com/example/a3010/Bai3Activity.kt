package com.example.a3010

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Bai3Activity : AppCompatActivity() {

    private lateinit var etMSSV: EditText
    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var calendarView: CalendarView
    private lateinit var tvSelectedDate: TextView // TextView để hiển thị ngày đã chọn
    private lateinit var spinnerWard: Spinner
    private lateinit var spinnerDistrict: Spinner
    private lateinit var spinnerCity: Spinner
    private lateinit var cbSports: CheckBox
    private lateinit var cbMovies: CheckBox
    private lateinit var cbMusic: CheckBox
    private lateinit var cbAgree: CheckBox
    private lateinit var btnSubmit: Button
    private lateinit var btnToggleCalendar: Button
    private var selectedDate: String = ""
    private lateinit var addressHelper: AddressHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b3)

        // Ánh xạ các View
        etMSSV = findViewById(R.id.etMSSV)
        etName = findViewById(R.id.etName)
        radioGroupGender = findViewById(R.id.radioGroupGender)
        etEmail = findViewById(R.id.etEmail)
        etPhone = findViewById(R.id.etPhone)
        calendarView = findViewById(R.id.calendarView)
        tvSelectedDate = findViewById(R.id.tvSelectedDate) // Khởi tạo TextView
        spinnerWard = findViewById(R.id.spinnerWard)
        spinnerDistrict = findViewById(R.id.spinnerDistrict)
        spinnerCity = findViewById(R.id.spinnerCity)
        cbSports = findViewById(R.id.cbSports)
        cbMovies = findViewById(R.id.cbMovies)
        cbMusic = findViewById(R.id.cbMusic)
        cbAgree = findViewById(R.id.cbAgree)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnToggleCalendar = findViewById(R.id.btnToggleCalendar)

        // Khởi tạo AddressHelper
        addressHelper = AddressHelper(this)

        // Lấy danh sách tỉnh và gán vào spinnerCity
        val provinces = addressHelper.getProvinces()
        val adapterCity = ArrayAdapter(this, android.R.layout.simple_spinner_item, provinces)
        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCity.adapter = adapterCity

        // Lắng nghe sự kiện chọn tỉnh
        spinnerCity.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedProvince = provinces[position]
                val districts = addressHelper.getDistricts(selectedProvince)
                val adapterDistrict = ArrayAdapter(this@Bai3Activity, android.R.layout.simple_spinner_item, districts)
                adapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerDistrict.adapter = adapterDistrict
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        })

        // Lắng nghe sự kiện chọn quận/huyện
        spinnerDistrict.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedDistrict = spinnerDistrict.selectedItem.toString()
                val wards = addressHelper.getWards(spinnerCity.selectedItem.toString(), selectedDistrict)
                val adapterWard = ArrayAdapter(this@Bai3Activity, android.R.layout.simple_spinner_item, wards)
                adapterWard.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerWard.adapter = adapterWard
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        })

        // Thiết lập CalendarView ẩn/hiện
        btnToggleCalendar.setOnClickListener {
            calendarView.visibility = if (calendarView.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }

        // Lắng nghe sự kiện chọn ngày từ CalendarView
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = "$dayOfMonth/${month + 1}/$year"
            tvSelectedDate.text = "Ngày sinh: $selectedDate" // Cập nhật TextView
            calendarView.visibility = View.GONE // Ẩn CalendarView sau khi chọn
        }

        // Lắng nghe sự kiện nút Submit
        btnSubmit.setOnClickListener { validateAndSubmit() }
    }

    private fun validateAndSubmit() {
        // Kiểm tra các trường bắt buộc
        val mssv = etMSSV.text.toString().trim()
        val name = etName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val phone = etPhone.text.toString().trim()
        val genderSelected = radioGroupGender.checkedRadioButtonId != -1
        val agreeChecked = cbAgree.isChecked

        if (mssv.isEmpty() || name.isEmpty() || email.isEmpty() || phone.isEmpty() || !genderSelected || selectedDate.isEmpty() || !agreeChecked) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin và đồng ý điều khoản", Toast.LENGTH_LONG).show()
            return
        }

        // Hiển thị thông tin đã nhập
        Toast.makeText(this, "Thông tin đã được gửi thành công!", Toast.LENGTH_SHORT).show()
    }
}
