pipeline {
  agent any
  stages {
    stage('Build') { steps { sh './gradlew clean build -x test' } }
    stage('Test') { steps { sh './gradlew test' } }
    stage('Dockerize') { steps { sh 'echo "docker build/push here..."' } }
    stage('Deploy') { steps { sh 'echo "kubectl apply -f manifests/ ..."'} }
  }
  post { always { junit 'build/test-results/test/*.xml' } }
}