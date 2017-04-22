Rails.application.routes.draw do

  # get '/login' => 'sessions#new'
  # post '/login' => 'sessions#create'
  # get '/logout' => 'sessions#destroy'
  
  # users controller
  get '/users' => 'users#index'
  put '/users/:user_id' => 'users#update'
  post '/users' => 'users#create'
  get '/users/:username/tutorial' => 'users#getActiveTutorial', constraints: { username: /[0-z\.]+/}
  get '/users/:username/tutorials' => 'users#getAllTutorials', constraints: { username: /[0-z\.]+/}
  get '/users/:username/tutorials/:name/attendances' => 'users#getTutAttendances', constraints: { username: /[0-z\.]+/}

  # tutorials controller
  get '/tutorials' => 'tutorials#index'
  get '/tutorials/:id' => 'tutorials#show'
  post '/tutorials' => 'tutorials#create'
  put '/tutorials/:id' => 'tutorials#update'
  get '/tutorials/:name/room' => 'tutorials#findRoom'
  get '/tutorials/:name/students' => 'tutorials#getAllStudents'
  get '/tutorials/:name/attendances' => 'tutorials#getAllAttendances'

  # rooms controller
  get '/rooms' => 'rooms#index'
  post '/rooms' => 'rooms#create'
  get 'rooms/:name/beacons' => 'rooms#getBeacons', constraints: { name: /[0-z\.]+/}

  # beacons controller
  get '/beacons' => 'beacons#index'
  post '/beacons' => 'beacons#create'

  # attendances controller
  get '/attendances' => 'attendances#index'
  post '/attendances' => 'attendances#create'
  put '/attendances/:id' => 'attendances#update'


end
