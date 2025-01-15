package com.example.afterschool2

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.afterschool2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer //음악 재생 미디어 플레이어 객체
    //UI 업데이트를 위한 핸들리
    private val handler = Handler(Looper.getMainLooper())
    private var isPaused = false //일시 정지 체크
    var selectedPosition = -1 //선택된 음악 항목의 위치

    val musicList = listOf(
        MusicItem("Music 1", "Artist 1", R.raw.song1),
        MusicItem("Music 2", "Artist 2", R.raw.song2),
        MusicItem("Music 3", "Artist 3", R.raw.song3)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //뷰 바인딩 객체 초기화
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = MusicAdapter(musicList) { music, position ->
            playMusic(music) //클릭 시 음악 재생
            updateSelectedPosition(position) //선택된 항목 업데이트
        }

        binding.btnPause.setOnClickListener {
            if (::mediaPlayer.isInitialized) {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause() //일시정지
                    isPaused = true
                }
                else if (isPaused) {
                    mediaPlayer.start() //다시 실행
                    isPaused = false
                }
            }
        }

        binding.btnStop.setOnClickListener {
            if (::mediaPlayer.isInitialized) {
                mediaPlayer.stop()
                mediaPlayer.reset() //초기화
                isPaused = false
                resetUI() //UI 초기화
            }
        }

        //SeekBar: 사용자가 직접 시크바를 조정하여 음악의 위치를 변경
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser && ::mediaPlayer.isInitialized) {
                    mediaPlayer.seekTo(progress) //미디어 플레이어 위치 조정
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        //반복 재생 체크박스 : 반복 재생 여부 설정
        binding.repeatChk.setOnCheckedChangeListener { _, isChecked ->
            if (::mediaPlayer.isInitialized) {
                mediaPlayer.isLooping = isChecked //반복 재생 설정
            }
        }
    }

    private fun playMusic(music: MusicItem) { //선택한 음악 재생
        if (::mediaPlayer.isInitialized) { mediaPlayer.release() } //기존 미디어 플레이어 해제

        mediaPlayer = MediaPlayer.create(this, music.resId) //새로운 미디어 플레이어 생성
        binding.txtTitle.text = music.title
        binding.seekBar.max = mediaPlayer.duration //시크바 최대값을 음악 길이로 설정

        mediaPlayer.start()
        isPaused = false
        updateSeekBar() //시크바 업데이트

        mediaPlayer.setOnCompletionListener { //음악이 끝날 때 UI 초기화 (반복 재생 아니면)
            if (!mediaPlayer.isLooping) {
                resetUI() //UI 초기화
            }
        }
    }

    private fun updateSeekBar() { //현재 재생 시간을 시크바에 반영하고 UI 갱신
        if (::mediaPlayer.isInitialized) {
            binding.seekBar.progress = mediaPlayer.currentPosition //현재 재생 위치로 시크바 진행
            val currentTime = formatTime(mediaPlayer.currentPosition) //현재 시간 포맷
            val totalTime = formatTime(mediaPlayer.duration) //총 시간 포맷
            binding.txtTime.text = "$currentTime / $totalTime"
            handler.postDelayed({updateSeekBar()}, 500) //500ms마다 업데이트 (자바 스레드의 기능)
        }
    }

    private fun updateSelectedPosition(position: Int) { //선택된 음악 항목 위치 업데이트
        val previousPosition = selectedPosition
        selectedPosition = position
        binding.recyclerView.adapter?.notifyItemChanged(previousPosition) //이전 항목 변경
        binding.recyclerView.adapter?.notifyItemChanged(selectedPosition) //새 항목 변경
    }

    private fun clearSelectedPosition() { //선택된 음악 항목 초기화
        val previousPosition = selectedPosition
        selectedPosition = -1
        binding.recyclerView.adapter?.notifyItemChanged(previousPosition)
    }

    private fun resetUI() { //UI 초기화
        clearSelectedPosition()
        binding.txtTitle.text = ""
        binding.txtTime.text = ""
        binding.seekBar.progress = 0
    }

    //밀리초를 분:초 형식으로 변환하는 함수
    private fun formatTime(milliseconds: Int): String {
        val minutes = milliseconds/1000/60
        val seconds = (milliseconds/1000)%60
        return String.format("%02d:%02d", minutes, seconds)
    }

    override fun onDestroy() {
        super.onDestroy() //종료 시 미디어 플레이어 해제
        if (::mediaPlayer.isInitialized) { mediaPlayer.release() }
        handler.removeCallbacksAndMessages(null) //핸들러의 모든 콜백 제거 (스레드 동작 정지)
    }
}
