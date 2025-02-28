package org.connectedsystems.datamodels;

import com.google.gson.annotations.SerializedName;

import java.net.URI;

public class Link {
    protected URI href;
    @SerializedName("rel")
    protected String relationType;
    @SerializedName("type")
    protected String mediaType;
    @SerializedName("hreflang")
    protected String hrefLanguage;
    protected String title;
    protected URI uid;
    @SerializedName("rt")
    protected URI resourceType;
    @SerializedName("if")
    protected URI interfaceUri;

    /**
     * URL of the target resource.
     */
    public URI getHref() {
        return href;
    }

    /**
     * Link relation type.
     */
    public String getRelationType() {
        return relationType;
    }

    /**
     * Media type of the target resource.
     */
    public String getMediaType() {
        return mediaType;
    }

    /**
     * Language tag of the target resource (2-letter language code, followed by optional 2-letter region code).
     */
    public String getHrefLanguage() {
        return hrefLanguage;
    }

    /**
     * Title of the target resource.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Unique identifier of the target resource
     */
    public URI getUid() {
        return uid;
    }

    /**
     * Semantic type of the target resource (RFC 6690).
     */
    public URI getResourceType() {
        return resourceType;
    }

    /**
     * Interface used to access the target resource (RFC 6690).
     */
    public URI getInterfaceUri() {
        return interfaceUri;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        sb.append("href: ").append(href).append(", ");
        if (relationType != null && !relationType.isEmpty())
            sb.append("rel: ").append(relationType).append(", ");
        if (mediaType != null && !mediaType.isEmpty())
            sb.append("type: ").append(mediaType).append(", ");
        if (hrefLanguage != null && !hrefLanguage.isEmpty())
            sb.append("hreflang: ").append(hrefLanguage).append(", ");
        if (title != null && !title.isEmpty())
            sb.append("title: ").append(title).append(", ");
        if (uid != null)
            sb.append("uid: ").append(uid).append(", ");
        if (resourceType != null)
            sb.append("rt: ").append(resourceType).append(", ");
        if (interfaceUri != null)
            sb.append("if: ").append(interfaceUri).append(", ");
        // Remove the last comma and space
        if (sb.length() > 1 && sb.charAt(sb.length() - 2) == ',') {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder for Link objects.
     */
    public static class Builder {
        private static final String HREF_LANGUAGE_REGEX = "^([a-z]{2}(-[A-Z]{2})?)|x-default$";
        private final Link link;

        public Builder() {
            link = new Link();
        }

        /**
         * URL of the target resource.
         */

        public Builder setHref(URI href) {
            link.href = href;
            return this;
        }

        /**
         * Link relation type.
         */
        public Builder setRelationType(String relationType) {
            link.relationType = relationType;
            return this;
        }

        /**
         * Media type of the target resource.
         */
        public Builder setMediaType(String mediaType) {
            link.mediaType = mediaType;
            return this;
        }

        /**
         * Language tag of the target resource (2-letter language code, followed by optional 2-letter region code).
         */
        public Builder setHrefLanguage(String hrefLanguage) {
            link.hrefLanguage = hrefLanguage;
            return this;
        }

        /**
         * Title of the target resource.
         */
        public Builder setTitle(String title) {
            link.title = title;
            return this;
        }

        /**
         * Unique identifier of the target resource
         */
        public Builder setUid(URI uid) {
            link.uid = uid;
            return this;
        }

        /**
         * Semantic type of the target resource (RFC 6690).
         */
        public Builder setResourceType(URI resourceType) {
            link.resourceType = resourceType;
            return this;
        }

        /**
         * Interface used to access the target resource (RFC 6690).
         */
        public Builder setInterfaceUri(URI interfaceUri) {
            link.interfaceUri = interfaceUri;
            return this;
        }

        /**
         * Builds the Link object.
         *
         * @return the built Link object
         * @throws IllegalStateException if href is not set or if hrefLanguage is not a valid language tag
         */
        public Link build() {
            if (link.href == null)
                throw new IllegalStateException("href must be set.");
            if (link.hrefLanguage != null && !link.hrefLanguage.matches(HREF_LANGUAGE_REGEX))
                throw new IllegalStateException("hrefLanguage must be a valid language tag (2-letter language code, followed by optional 2-letter region code).");

            return link;
        }
    }
}
