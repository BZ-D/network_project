import axios from "axios";

export const searchByTitle = payload => {
  console.log(payload)
  return axios.post('${QUESTIONARE_MODULE}/searchByTitle', {
    params: {
      titleQN
    }
  }).then(res => {
    return res.data
  })
}

export const searchByUser = payload => {
  console.log(payload)
  return axios.post('${QUESTIONARE_MODULE}/searchByUser/${uid}').then(res => {
    return res.data
  })
}
export const checkFilled = payload => {
  console.log(payload)
  return axios.post('${QUESTIONARE_MODULE}/checkFilled/${uid}').then(res => {
    return res.data
  })
}
export const checkReleased = payload => {
  console.log(payload)
  return axios.post('${QUESTIONARE_MODULE}/checkReleased/${uid}').then(res => {
    return res.data
  })
}
export const checkDraft = payload => {
  console.log(payload)
  return axios.post('${QUESTIONARE_MODULE}/checkDraft/${uid}').then(res => {
    return res.data
  })
}
export const release = payload => {
  console.log(payload)
  const {
    createUserID,
      titleOfQn,
    isDraft,
    numOfQuestions
  } = payload
  return axios.post('${QUESTIONARE_MODULE}/release', {
    createUserID,
    titleOfQn,
    isDraft,
    numOfQuestions
  }).then(res => {
    return res.data
  })
}

export const draft = payload => {
  console.log(payload)
  const {
    createUserID,
    titleOfQn,
    isDraft,
    numOfQuestions
  } = payload
  return axios.post('${QUESTIONARE_MODULE}/draft', {
    createUserID,
    titleOfQn,
    isDraft,
    numOfQuestions
  }).then(res => {
    return res.data
  })
}
