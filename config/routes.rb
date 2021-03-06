Rails.application.routes.draw do

  # sessions controller
  post   '/login' => 'sessions#create'
  delete '/logout' => 'sessions#destroy'
  
  # users controller
  get '/users' => 'users#index'
  get '/users/:username' => 'users#show', constraints: { username: /[0-z\.]+/}
  put '/users/:id' => 'users#update'
  put '/users/:username/makeAttended' => 'users#makeAttended', constraints: { username: /[0-z\.]+/}
  post '/users' => 'users#create'
  get '/users/:username/activeTutorial' => 'users#getActiveTutorial', constraints: { username: /[0-z\.]+/}
  get '/users/:username/allTutorials' => 'users#getAllTutorials', constraints: { username: /[0-z\.]+/}
  get '/users/:username/tutorials/:name/attendances' => 'users#getTutAttendances', constraints: { username: /[0-z\.]+/}

  # tutorials controller
  get '/tutorials' => 'tutorials#index'
  get '/tutorials/:name' => 'tutorials#show'
  post '/tutorials' => 'tutorials#create'
  put '/tutorials/:name/update' => 'tutorials#update'
  post '/tutorials/:name/generateAttendances' => 'tutorials#generateAttendances'
  get '/tutorials/:name/room' => 'tutorials#findRoom'
  get '/tutorials/:name/students' => 'tutorials#getAllStudents'
  get '/tutorials/:name/attendances' => 'tutorials#getAllAttendances'
  put '/tutorials/:name/endTime' => 'tutorials#enterEndTime'

  # rooms controller
  get '/rooms' => 'rooms#index'
  get '/rooms/:name' => 'rooms#show', constraints: { name: /[0-z\.]+/}
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
