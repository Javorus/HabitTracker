import React, { useState, useEffect, createContext, useContext } from "react";
import { useAuth } from "./authContext";
import { createTagApi, getAllTagsForUserIdApi } from "./api/TagApi";

export const TagContext = createContext();

export const useTag = () => useContext(TagContext);

export default function TagComponent({ onTagsUpdate }) {
  const [tagName, setTagName] = useState("");
  const [tags, setTags] = useState([]);
  const authContext = useAuth();

  useEffect(() => {
    retrieveTags();
  }, []);

  function retrieveTags() {
    getAllTagsForUserIdApi(authContext.user)
      .then((response) => {
        setTags(response.data);
        console.log(response.data);
        onTagsUpdate(response.data);
      })
      .catch((error) => console.log(error));
  }

  return (
    <TagContext.Provider value={{ tags, setTags, retrieveTags }}>
    <div>
        {/* Render your component content here */}
        <h2>Tags</h2>
        <ul>
          {tags.map((tag) => (
            <li key={tag.tagId}>{tag.tagName}</li>
          ))}
        </ul>
      </div>
    </TagContext.Provider>
  );
}