Rails.application.routes.draw do

  # get '/login' => 'sessions#new'
  # post '/login' => 'sessions#create'
  # get '/logout' => 'sessions#destroy'
  
  # users controller
  get '/users' => 'users#index'
  put '/users/:user_id' => 'users#update'
  post '/users' => 'users#create'
  get '/users/:username/tutorial' => 'users#getActiveTutorial'
  get '/users/:username/tutorials' => 'users#getAllTutorials'
  get '/users/:username/tutorials/:name/attendances' => 'users#getTutAttendances'

  # tutorials controller
  post '/tutorials' => 'tutorials#create'
  put '/tutorials/:tutorial_id' => 'tutorials#update'
  get '/tutorials/:name/room' => 'tutorials#findRoom'
  get '/tutorials/:name/students' => 'tutorials#getAllStudents'
  get '/tutorials/:name/attendances' => 'tutorials#getAllAttendances'

  # rooms controller
  post '/rooms' => 'rooms#create'
  get 'rooms/:name/beacons' => 'rooms#getBeacons'

  # beacons controller
  post '/beacons' => 'beacons#create'

  # attendances controller
  post '/attendances' => 'attendances#create'
  put '/attendances/:id' => 'attendances#update'


end
