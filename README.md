Nama: Karel Tsalasatir Riyan <br>
NIM: H1D023085 <br>
Shift Baru: F <br>
Shift Asal: E <br><br>

Video Demo:<br>
![Screenshot Aplikasi Saya](Screen_recording_20251028_120813.gif)
<br>
Penjelasan Alur Data:<br>
 Ringkasan Alur                                                                                                                                              
                                                                                                                                                              
  - UI memicu pengambilan data (mis. buka PlayerActivity/CoachActivity).                                                                                      
  - ViewModel menjalankan coroutine dan minta data ke Repository.                                                                                             
  - Repository memanggil endpoint Retrofit dengan token.                                                                                                      
  - API mengembalikan TeamResponse (berisi coach dan squad).                                                                                                  
  - ViewModel mengisi LiveData (team/error).                                                                                                                  
  - UI mengamati LiveData dan mengikat data ke tampilan (list/detail).                                                                                        
                                                                                                                                                              
  Konfigurasi & Model                                                                                                                                         
                                                                                                                                                              
  - Base URL dan kredensial:                                                                                                                                  
      - app/src/main/java/com/example/responsi1mobileh1d023085/utils/Constants.kt:1 (BASE_URL, TEAM_ID, API_TOKEN)                                            
  - Model respons:                                                                                                                                            
      - app/src/main/java/com/example/responsi1mobileh1d023085/data/model/TeamResponse.kt:1 (punya coach: CoachResponse? dan squad: List<PlayerResponse>)     
      - app/src/main/java/com/example/responsi1mobileh1d023085/data/model/PlayerResponse.kt:1                                                                 
      - app/src/main/java/com/example/responsi1mobileh1d023085/data/model/CoachResponse.kt:1                                                                  
                                                                                                                                                              
  Jaringan (Retrofit)                                                                                                                                         
                                                                                                                                                              
  - Interface endpoint:                                                                                                                                       
      - app/src/main/java/com/example/responsi1mobileh1d023085/data/network/FootballApi.kt:10                                                                 
          - GET teams/{id} + header X-Auth-Token                                                                                                              
  - Builder Retrofit:                                                                                                                                         
      - app/src/main/java/com/example/responsi1mobileh1d023085/data/network/RetrofitInstance.kt:9                                                             
          - .baseUrl(Constants.BASE_URL) + GsonConverterFactory                                                                                               
                                                                                                                                                              
  Repository                                                                                                                                                  
                                                                                                                                                              
  - Abstraksi akses data jaringan:                                                                                                                            
      - app/src/main/java/com/example/responsi1mobileh1d023085/data/repository/TeamRepository.kt:6                                                            
          - fetchTeam(teamId) → RetrofitInstance.api.getTeam(teamId, Constants.API_TOKEN)                                                                     
                                                                                                                                                              
  ViewModel                                                                                                                                                   
                                                                                                                                                              
  - Sumber kebenaran data untuk UI (LiveData):                                                                                                                
      - app/src/main/java/com/example/responsi1mobileh1d023085/viewmodel/PlayerViewModel.kt:13                                                                
          - State: team: LiveData<TeamResponse?>, error: LiveData<String?>                                                                                    
          - loadTeam(id: Int = Constants.TEAM_ID) (baris 22):                                                                                                 
              - Jalankan coroutine (viewModelScope.launch)                                                                                                    
              - Panggil repo.fetchTeam(id)                                                                                                                    
              - Jika sukses → _team.value = res.body()                                                                                                        
              - Jika gagal → _error.value = "HTTP ${res.code()}" atau exception message                                                                       
                                                                                                                                                              
  Penyajian di Layar                                                                                                                                          
                                                                                                                                                              
  - Daftar pemain:                                                                                                                                            
      - app/src/main/java/com/example/responsi1mobileh1d023085/PlayerActivity.kt:12
          - Inisialisasi RecyclerView + PlayerAdapter                                                                                                         
          - Observasi viewModel.team → adapter.setData(team.squad) (baris 26–27)                                                                              
          - Memicu viewModel.loadTeam() (baris 35)                                                                                                            
      - Klik item → detail sebagai BottomSheet:                                                                                                               
          - Adapter panggil callback → DetailPlayersFragment.newInstance(player.id).show(...)                                                                 
              - app/src/main/java/com/example/responsi1mobileh1d023085/PlayerActivity.kt:16                                                                   
  - Detail pemain (BottomSheet):                                                                                                                              
      - app/src/main/java/com/example/responsi1mobileh1d023085/ui/fragment/DetailPlayersFragment.kt:12                                                        
          - Ambil player_id dari argumen (baris 23)                                                                                                           
          - Observasi vm.team (shared dengan Activity) → cari pemain sesuai ID dari team.squad                                                                
          - Set teks ke tvName, tvBirth, tvNation, tvPos (baris 28–31)                                                                                        
  - Data pelatih (Coach):                                                                                                                                     
      - app/src/main/java/com/example/responsi1mobileh1d023085/CoachActivity.kt:12                                                                            
          - Observasi vm.team → isi tvCoachName, tvCoachDob, tvCoachNation (baris 28–32)                                                                      
          - Panggil vm.loadTeam() (baris 35)                                                                                                                  
  - Navigasi dari beranda:                                                                                                                                    
      - app/src/main/java/com/example/responsi1mobileh1d023085/MainActivity.kt:8                                                                              
          - Klik “Coach” → buka CoachActivity (baris 48–49)                                                                                                   
          - Klik “Team” → buka PlayerActivity (baris 54–55)                                                                                                   
                                                                                                                                                              
  Error Handling & Lifecycle                                                                                                                                  
                                                                                                                                                              
  - Error dari jaringan dikirim lewat PlayerViewModel.error (tidak ditampilkan di UI saat ini, tetapi tersedia untuk diobservasi).                            
  - Pengamatan LiveData memakai lifecycle (this/viewLifecycleOwner) sehingga UI hanya update saat aktif dan aman dari leak.                                   
                                                                                                                               
