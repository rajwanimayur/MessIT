pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps {
        git(url: 'https://github.com/rajwanimayur/MessIT.git', changelog: true, poll: true)
      }
    }
    stage('Build') {
      steps {
        build 'MessITBuild'
      }
    }
    stage('Test'){
      steps{
        build 'MessIT_Test'
      }
    }
    stage('Deploy'){
    	steps{
    		build 'MessIT_Deploy'
    	}
    }
  }
}
