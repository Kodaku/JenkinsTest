pipeline {
    agent any
    stages {
        stage("Creating A User In The Database") {
            steps {
                script {
                    def response = httpRequest acceptType: 'APPLICATION_JSON', contentType: 'APPLICATION_JSON', httpMode: 'POST', quiet: true, requestBody: '''
                                    {
                                        "firbaseId" : "rhfkerhf",
                                        "userName": "Franco",
                                        "email" : "franco@franco.com"
                                    }
                                    ''', url: 'http://localhost:5000/api/users/add'
                    def status = response.status
                    def content = response.content
                    def contentJson = readJSON text: content
                    assert status as int == 201
                    println contentJson
                    def response2 = httpRequest "http://localhost:5000/api/users/delete/${contentJson.user.id}"
                    assert response2.status as int == 200
                }
            }
        }
    }
}